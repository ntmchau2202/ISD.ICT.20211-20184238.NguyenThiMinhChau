package views;

import java.io.IOException;
import java.sql.SQLException;

import controllers.AIMSBaseController;
import controllers.PlaceNormalOrderController;
import controllers.PlaceOrderController;
import exceptions.aims.InvalidDeliveryException;
import exceptions.aims.InvalidMediaInfoException;
import exceptions.aims.MediaUpdateException;
import exceptions.aims.PlaceOrderException;
import exceptions.interbank.InterbankException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;

/**
 * 
 * @author Hikaru
 *
 */
public class PaymentScreenHandler extends AIMSBaseScreenHandler  {

	/***
	 * Initialize the Payment method screen 
	 * @param prevScreen a handler to the screen that called this screen
	 * @throws IOException 
	 */
	
	@FXML
	private TextField cardHolderNameTxtFld;
	@FXML
	private TextField cardNumberTxtFld;
	@FXML
	private TextField expirationDateTxtFld;
	@FXML
	private TextField securityCodeTxtFld;
	@FXML
	private Button confirmBtn;
	
	protected PaymentScreenHandler(Stage stage, AIMSBaseScreenHandler prevScreen) throws IOException {
		super(stage, prevScreen, Configs.SCREEN_PATH_PAYMENT);
		initialize();
	}

	@Override
	protected void initialize() {
		confirmBtn.setOnMouseClicked(e -> {
			try {
				validateInput();
				PlaceOrderController.getPlaceOrderController().validateCard(cardHolderNameTxtFld.getText(), cardNumberTxtFld.getText(), securityCodeTxtFld.getText(), expirationDateTxtFld.getText());
				PlaceOrderController.getPlaceOrderController().placeOrder();
				PlaceOrderSuccessScreenHandler thankHandler = new PlaceOrderSuccessScreenHandler(this.stage, this);
				thankHandler.show();
			} catch (PlaceOrderException | InterbankException | InvalidMediaInfoException | SQLException | MediaUpdateException e1) {
				// Invalid card holder name
				try {
					e1.printStackTrace();
					this.notifyError(e1.getMessage());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	private void validateInput() throws PlaceOrderException {
		if(cardHolderNameTxtFld.getText().length() == 0 ||
				cardNumberTxtFld.getText().length() == 0 ||
				expirationDateTxtFld.getText().length() == 0 ||
				securityCodeTxtFld.getText().length() != 3) {
			throw new PlaceOrderException("Invalid field, please check again");
		}
	}

}
