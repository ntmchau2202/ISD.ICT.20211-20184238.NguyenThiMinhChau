package views;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;

public class PopupScreenHandler extends AIMSBaseScreenHandler {
	
	@FXML
	private ImageView image;
	@FXML
	private Label messageLbl;

	private String iconPath;
	private String message;
	protected PopupScreenHandler(Stage newStage, String iconPath, String message) throws IOException {
		super(newStage, null, Configs.SCREEN_PATH_POPUP);
		this.iconPath = iconPath;
		this.message = message;
		initialize();
	}

	@Override
	protected void initialize() {
		String img = new File(iconPath).toURI().toString();
		System.out.println("Icon to be loaded: " + img);
		image.setImage(new Image(img));    		
		messageLbl.setText(message);
		messageLbl.setWrapText(true);
		messageLbl.setContentDisplay(ContentDisplay.CENTER);
		messageLbl.setAlignment(Pos.CENTER);
	}
}
