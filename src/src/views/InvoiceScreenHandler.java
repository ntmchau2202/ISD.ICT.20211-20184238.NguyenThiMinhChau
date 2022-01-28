package views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map.Entry;

import controllers.InformationController;
import controllers.PlaceNormalOrderController;
import entities.Order;
import entities.medias.Media;
import exceptions.aims.InvalidMediaInfoException;
import exceptions.aims.MediaUpdateException;
import exceptions.aims.PlaceOrderException;
import exceptions.interbank.InterbankException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.CartScreenHandler.ItemInCartScreenHandler;

public class InvoiceScreenHandler extends AIMSBaseScreenHandler {
	@FXML
	private VBox itemsVBox;
	@FXML
	private Label subtotalLbl;
	@FXML
	private Label vatLbl;
	@FXML
	private Label shippingFeeLbl;
	@FXML
	private Label totalLbl;
	@FXML
	private Label nameLbl;
	@FXML
	private Label phoneLbl;
	@FXML
	private Label cityLbl;
	@FXML
	private Label addressLbl;
	@FXML
	private Label instructionLbl;
	@FXML
	private Button confirmBtn;
	@FXML
	private ImageView backBtn;
	
	private Order order;
	public InvoiceScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, Order order)
			throws IOException {
		super(newStage, prevScreen, Configs.SCREEN_PATH_INVOICE);
		this.order = order;
		initialize();
	}

	@Override
	protected void initialize() {
		subtotalLbl.setText(Double.toString(order.getAmount()) + Configs.CURRENCY);
		vatLbl.setText(Double.toString(order.getVat()) + Configs.CURRENCY);
		shippingFeeLbl.setText(Double.toString(order.getShippingFee()) + Configs.CURRENCY);
		totalLbl.setText(Double.toString(order.getTotal()) + Configs.CURRENCY);
		
		nameLbl.setText(order.getCustomer().getCustomerName());
		phoneLbl.setText(order.getCustomer().getPhone());
		cityLbl.setText(order.getCustomer().getCity());
		addressLbl.setText(order.getCustomer().getAddress());
		instructionLbl.setText(order.getInstruction());
		backBtn.setOnMouseClicked(e->this.returnToPreviousScreen());
		Iterator<Entry<Media, Integer>> it = order.getListItemOrdered().entrySet().iterator();
		try {
			while(it.hasNext()) {
				Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
				System.out.println(entry.getKey().getTitle());
				ItemInOrderScreenHandler itemHandler = new ItemInOrderScreenHandler(this.stage, this, entry.getKey(), entry.getValue());
				itemsVBox.getChildren().add(itemHandler.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		confirmBtn.setOnMouseClicked(e-> {
			try {
				PaymentScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, this);
				paymentScreen.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public class ItemInOrderScreenHandler extends AIMSBaseScreenHandler {
		@FXML
		private Label priceLbl;
		@FXML
		private Label nameLbl;
		@FXML
		private Label quantityLbl;
		@FXML
		private Label totalPriceLbl;
		@FXML
		private ImageView image;
		
		private Media media;
		private int quantity;
		protected ItemInOrderScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, Media media, int quantity)
				throws IOException {
			super(newStage, prevScreen, Configs.SCREEN_PATH_ITEM_IN_ORDER);
			this.media = media;
			this.quantity = quantity;
			initialize();
		}

		@Override
		protected void initialize() {
			nameLbl.setText(media.getTitle());
			priceLbl.setText(Double.toString(media.getPrice()) + Configs.CURRENCY);
			quantityLbl.setText(Integer.toString(quantity));
			totalPriceLbl.setText(Double.toString(media.getPrice() * quantity) + Configs.CURRENCY);
			image.setImage(new Image(new File(media.getImagePath()).toString()));
		}
		
	}

}
