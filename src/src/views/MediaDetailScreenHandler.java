package views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import controllers.CartController;
import entities.medias.*;
import exceptions.aims.InvalidMediaInfoException;
import exceptions.aims.MediaUpdateException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;

public class MediaDetailScreenHandler extends AIMSBaseScreenHandler {
	@FXML
	private Label mediaNameHeaderLbl;
	@FXML
	private ImageView image;
	@FXML
	private Label mediaNameLbl;
	@FXML
	private Label mediaTypeLbl;
	@FXML
	private Label mediaPriceLbl;
	@FXML
	private Label mediaAvailableQuantityLbl;
	@FXML
	private Label authorsLbl;
	@FXML
	private Label lengthTitleLbl;
	@FXML
	private Label lengthLbl;
	@FXML
	private Label numOfTracksLbl;
	@FXML
	private Label numOfTracksTitleLbl;
	@FXML
	private Button addToCartBtn;
	@FXML
	private TextField selectorTxtFld;
	@FXML
	private ImageView backBtn;
	
	private Media currentMedia;
	private int availableQuantity;
	protected MediaDetailScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, Media media, int quantity) throws IOException {
		super(newStage, prevScreen, Configs.SCREEN_PATH_ITEM_DETAILS);
		this.currentMedia = media;
		this.availableQuantity = quantity;
		initialize();
	}

	@Override
	protected void initialize() {
//		quantitySelector = new Spinner<Integer>();
//		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, availableQuantity);
//		quantitySelector.setValueFactory(valueFactory);
		backBtn.setOnMouseClicked(e->this.returnToPreviousScreen());
    	if(currentMedia.getImagePath() != null && currentMedia.getImagePath().length() != 0) {
    		String img = new File(currentMedia.getImagePath()).toString();
    		image.setImage(new Image(img));    		
    	}
    	mediaTypeLbl.setText(currentMedia.getClass().toString().replaceAll("class entities.medias.", ""));
    	mediaNameHeaderLbl.setText(currentMedia.getTitle());
		selectorTxtFld.setText(Integer.toString(1));
		mediaNameLbl.setText(currentMedia.getTitle());
		mediaPriceLbl.setText(Double.toString(currentMedia.getPrice()) + Configs.CURRENCY);
		mediaAvailableQuantityLbl.setText("over " + Integer.toString(availableQuantity) + " available");
		// TODO: AIMS: display authors
		
		// switch for disc type
		if (this.currentMedia instanceof Disc) {
			lengthTitleLbl.setVisible(true);
			lengthLbl.setVisible(true);
			lengthLbl.setText(Double.toString(((Disc)currentMedia).getLength()) + Configs.TIME_UNIT);
			if (this.currentMedia instanceof CD) {
				numOfTracksTitleLbl.setVisible(true);
				numOfTracksLbl.setVisible(true);
				numOfTracksLbl.setText(Integer.toString(((CD)currentMedia).getNumOfTracks()));
			} else {
				numOfTracksTitleLbl.setVisible(false);
				numOfTracksLbl.setVisible(false);
			}
		} else {
			lengthTitleLbl.setVisible(false);
			lengthLbl.setVisible(false);
			numOfTracksTitleLbl.setVisible(false);
			numOfTracksLbl.setVisible(false);
		}
		
		addToCartBtn.setOnMouseClicked(e->{
			try {
				int selectedQuan = Integer.parseInt(selectorTxtFld.getText());
				CartController.getCartController().addItemToCart(currentMedia, selectedQuan);
				try {
					this.notifySuccess("Add item to cart successfuly");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (MediaUpdateException | InvalidMediaInfoException | SQLException e1) {
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
