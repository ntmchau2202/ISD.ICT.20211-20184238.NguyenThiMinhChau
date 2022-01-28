package views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import controllers.AIMSBaseController;
import controllers.CartController;
import controllers.InformationController;
import entities.medias.*;
import exceptions.aims.InvalidMediaInfoException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;

/**
 * 
 * @author Hikaru
 *
 */
public class AIMSMainScreenHandler extends AIMSBaseScreenHandler {
	@FXML
	private VBox bookVBox;
	@FXML
	private VBox discVBox;
	@FXML
	private TextField searchBarTxtFld;
	@FXML
	private Button searchBtn;
	@FXML
	private ImageView cartBtn;
	
	public class ItemInStoreHandler extends AIMSBaseScreenHandler{
		@FXML
		private Label availableNumTxt;
		@FXML
		private Label mediaNameLabel;
		@FXML
		private Label mediaPriceLabel;
		@FXML
		private Button viewDetailBtn;
		@FXML
		private ImageView mediaImgView;
		
		private Media currentMedia;
		private int quantity;
		protected ItemInStoreHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, Media media, int currentQuantity) throws IOException {
			super(newStage, prevScreen, Configs.SCREEN_PATH_ITEM_IN_STORE);
			this.currentMedia = media;
			this.quantity = currentQuantity;
			initialize();
		}

		@Override
		protected void initialize() {
	    	if(currentMedia.getImagePath() != null && currentMedia.getImagePath().length() != 0) {
	    		String img = new File(currentMedia.getImagePath()).toString();
	    		mediaImgView.setImage(new Image(img));    		
	    	}
			mediaNameLabel.setText(currentMedia.getTitle());
			mediaPriceLabel.setText(Double.toString(currentMedia.getPrice()) + Configs.CURRENCY);
			availableNumTxt.setText(Integer.toString(quantity));
			viewDetailBtn.setOnMouseClicked(e->{
				MediaDetailScreenHandler mediaDetail;
				try {
					mediaDetail = new MediaDetailScreenHandler(this.stage, this.getPreviousScreen(), currentMedia, quantity);
					mediaDetail.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
	}

	/***
	 * Initialize the main screen of AIMS
	 * @param prevScreen a handler to the screen that called this screen
	 * @throws IOException 
	 */
	public AIMSMainScreenHandler(Stage stage, AIMSBaseScreenHandler prevScreen) throws IOException {
		super(stage, null, Configs.SCREEN_PATH_MAIN);
		initialize();
	}
	
	
	
	/***
	 * Refresh the current medias in store with their corresponding available quantity
	 * @throws SQLException 
	 * @throws InvalidMediaInfoException 
	 */
	public void refreshMediaStore() throws InvalidMediaInfoException, SQLException {
		refreshBookStore(InformationController.getInformationController().getListBook());
		refreshDiscStore(InformationController.getInformationController().getListDisc());
	}
	
	private void refreshBookStore(HashMap<Book, Integer> listItem) {
		bookVBox.getChildren().clear();
		
		Iterator<Entry<Book, Integer>> it = listItem.entrySet().iterator();
		try {
			while(it.hasNext()) {
				Entry<Book,Integer> entry = (Entry<Book, Integer>)it.next();
				ItemInStoreHandler itemHandler = new ItemInStoreHandler(this.stage, this, entry.getKey(), entry.getValue());
				bookVBox.getChildren().add(itemHandler.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refreshDiscStore(HashMap<Disc, Integer> listItem) {
		discVBox.getChildren().clear();
		
		Iterator<Entry<Disc, Integer>> it = listItem.entrySet().iterator();
		try {
			while(it.hasNext()) {
				Entry<Disc,Integer> entry = (Entry<Disc, Integer>)it.next();
				ItemInStoreHandler itemHandler = new ItemInStoreHandler(this.stage, this, entry.getKey(), entry.getValue());
				discVBox.getChildren().add(itemHandler.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Search for a media item that have searchKey in its attributes and display result
	 * @param searchKey the key pharse used to search
	 */
	public void search() {
		String keyword = searchBarTxtFld.getText();
		try {
			Media media = InformationController.getInformationController().searchMediaByID(keyword);
			if (media != null) {
				// TODO: AIMS: do st to display here
				return;
			} else {
				HashMap<Media, Integer> resultSet = InformationController.getInformationController().searchMediaByName(keyword);
				if (resultSet.size() == 0) {
					// TODO: AIMS: display error here
				} else {
					// TODO: AIMS: display results here
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	@Override
	protected void initialize() {
		try {
			cartBtn.setOnMouseClicked(e -> {
				try {
					CartScreenHandler cartScreen = new CartScreenHandler(this.stage, this, CartController.getCartController().getCart());
					cartScreen.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			refreshMediaStore();
		} catch (InvalidMediaInfoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
