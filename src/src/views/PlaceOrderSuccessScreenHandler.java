package views;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;

public class PlaceOrderSuccessScreenHandler extends AIMSBaseScreenHandler {
	@FXML
	private Button backBtn;
	
	public PlaceOrderSuccessScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen)
			throws IOException {
		super(newStage, prevScreen, Configs.SCREEN_PATH_PAYMENT_NOTI);
		initialize();
	}

	@Override
	protected void initialize() {
		backBtn.setOnMouseClicked(e -> {
			AIMSMainScreenHandler mainScreen;
			try {
				mainScreen = new AIMSMainScreenHandler(new Stage(), this);
				mainScreen.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}

}
