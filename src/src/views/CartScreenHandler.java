package views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import controllers.InformationController;
import controllers.PlaceNormalOrderController;
import controllers.PlaceRushOrderController;
import entities.Cart;
import entities.medias.Media;
import exceptions.aims.MediaUpdateException;
import exceptions.aims.PlaceOrderException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;

/**
 * 
 * @author Hikaru
 *
 */
public class CartScreenHandler extends AIMSBaseScreenHandler {
	@FXML
	private VBox itemInCartVBox;
	@FXML
	private Label subtotalLbl;
	@FXML
	private Button placeOrderBtn;
	@FXML
	private ImageView backBtn;
	@FXML
	private CheckBox placeRushChkBx;

	
	private Cart cart;
	private double subtotal;
	private HashMap<Media, Integer> itemToBePurchased;
	class ItemInCartScreenHandler extends AIMSBaseScreenHandler {
		@FXML
		private Label mediaNameLbl;
		@FXML
		private Label mediaPriceLbl;
		@FXML
		private Label itemTotalLbl;
		@FXML
		private Button deleteBtn;
		@FXML
		private CheckBox selectItemBox;
		@FXML
		private ImageView image;
		@FXML
		private TextField selectQuantityTxtFld;
		@FXML
		private Label supportRushLbl;
		private Media media;
		private int currentSelectedQuantity, maxAvailableQuantity;
		private double totalPrice;
		public ItemInCartScreenHandler(Stage newStage, Media item, int currentSelectedQuantity, int maxAvailableQuantity) throws IOException {
			super(newStage, null, Configs.SCREEN_PATH_ITEM_IN_CART);
			this.media = item;
			this.currentSelectedQuantity = currentSelectedQuantity;
			this.maxAvailableQuantity = maxAvailableQuantity;
			initialize();
		}

		@Override
		protected void initialize() {
			image.setImage(new Image(new File(media.getImagePath()).toString()));
			mediaNameLbl.setText(media.getTitle());
			mediaPriceLbl.setText(Double.toString(media.getPrice()) + Configs.CURRENCY);
			selectQuantityTxtFld.setText(Integer.toString(currentSelectedQuantity));
			totalPrice = media.getPrice() * currentSelectedQuantity;
			itemTotalLbl.setText(Double.toString(totalPrice));
			supportRushLbl.setText(media.isRushSupported() ? "YES" : "NO");
			selectQuantityTxtFld.textProperty().addListener((observable, oldVal, newVal)-> {
				try {
					int newQuantity = 0;
					if (newVal.length() != 0) {
						newQuantity = Integer.parseInt(newVal);
					}
					totalPrice = media.getPrice() * newQuantity;
					itemTotalLbl.setText(Double.toString(totalPrice) + Configs.CURRENCY);
					if (itemToBePurchased.containsKey(media)) {
						if (newQuantity > 0) {
							itemToBePurchased.replace(media, newQuantity);							
						} else {
							itemToBePurchased.remove(media);
						}
					}
				} catch (Exception e) {
					// silence
				}
			});
			selectItemBox.selectedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					if(arg2) {
						subtotal += totalPrice;
						itemToBePurchased.put(media, Integer.parseInt(selectQuantityTxtFld.getText()));
					} else {
						subtotal -= totalPrice;
						itemToBePurchased.remove(media);
					}
					subtotalLbl.setText(Double.toString(subtotal) + Configs.CURRENCY);
				}
				
			});
			deleteBtn.setOnMouseClicked(e->{
				try {
					cart.deleteItemFromCart(media);
					refreshCart();
				} catch (MediaUpdateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
	}
	
	protected CartScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, Cart cart) throws IOException {
		super(newStage, prevScreen, Configs.SCREEN_PATH_CART);
		this.cart = cart;
		itemToBePurchased = new HashMap<Media, Integer>();
		initialize();
	}
	
	private void refreshCart() {
		itemInCartVBox.getChildren().clear();
		HashMap<Media, Integer> listMediaInCart = this.cart.getListMedia();
		subtotal = 0;
		Iterator<Entry<Media, Integer>> it = listMediaInCart.entrySet().iterator();
		try {
			while(it.hasNext()) {
				Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
				ItemInCartScreenHandler itemHandler = new ItemInCartScreenHandler(this.stage, entry.getKey(), entry.getValue(), InformationController.getInformationController().getListMedia().get(entry.getKey()));
				itemInCartVBox.getChildren().add(itemHandler.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initialize() {
		refreshCart();
		subtotalLbl.setText(Double.toString(subtotal));
		backBtn.setOnMouseClicked(e->this.returnToPreviousScreen());
		placeOrderBtn.setOnMouseClicked(e -> {
			try {				
				if(itemToBePurchased.isEmpty()) {
					this.notifyError("Please choose something to place order");
				} else {
					if (placeRushChkBx.isSelected()) {
						PlaceRushOrderController.getPlaceRushOrderController(itemToBePurchased);
						RushDeliveryScreenHandler rushDeliveryHandler = new RushDeliveryScreenHandler(this.stage, this, Configs.SCREEN_PATH_RUSH_DELIVERY);
						rushDeliveryHandler.show();
					} else {
						PlaceNormalOrderController.getPlaceNormalOrderController(itemToBePurchased);
						NormalDeliveryScreenHandler deliveryHandler = new NormalDeliveryScreenHandler(this.stage, this, Configs.SCREEN_PATH_NORMAL_DELIVERY);
						deliveryHandler.show();											
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (PlaceOrderException e1) {
				try {
					this.notifyError(e1.getMessage());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
	}

	

}
