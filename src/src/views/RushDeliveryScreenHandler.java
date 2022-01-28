package views;

import java.io.IOException;

import controllers.PlaceNormalOrderController;
import controllers.PlaceRushOrderController;
import exceptions.aims.InvalidDeliveryException;
import exceptions.aims.PlaceOrderException;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

public class RushDeliveryScreenHandler extends NormalDeliveryScreenHandler {
	
	protected RushDeliveryScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, String path) throws IOException {
		super(newStage, prevScreen, path);
		this.choices.clear();
		this.choices.add("Hanoi");
		initialize();
	}
	
	protected void initialize() {
		cityChoiceBox.setItems(FXCollections.observableArrayList(choices));
		submitBtn.setOnMouseClicked(e->{
			try {
				validateInput();
				// TODO: AIMS: support rush order here
				PlaceRushOrderController.getPlaceRushOrderController(null).addCustomer(nameTxtFld.getText(), cityChoiceBox.getValue().toString(), addressTxtFld.getText(), phoneTxtFld.getText());
				PlaceRushOrderController.getPlaceRushOrderController(null).addDeliveryInstruction(instructionTxtFld.getText());
				InvoiceScreenHandler invoiceScreen = new InvoiceScreenHandler(this.stage, this, PlaceRushOrderController.getPlaceRushOrderController(null).getOrder());
				invoiceScreen.show();
			} catch (InvalidDeliveryException e1) {
				try {
					this.notifyError(e1.getMessage());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (PlaceOrderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		backBtn.setOnMouseClicked(e -> this.returnToPreviousScreen());
	}
	
	void validateInput() throws InvalidDeliveryException, PlaceOrderException {
		if (nameTxtFld.getText().length() == 0) {
			throw new InvalidDeliveryException("Name must not be empty");
		}
		
		if (phoneTxtFld.getText().length() < 9 || phoneTxtFld.getText().length() > 11) {
			throw new InvalidDeliveryException("Phone length is not valid");
		}
		
		if (addressTxtFld.getText().length() == 0) {
			throw new InvalidDeliveryException("Address must not be empty");
		}
		
		PlaceRushOrderController.getPlaceRushOrderController(null).validateOrderInformation(nameTxtFld.getText(), phoneTxtFld.getText(), addressTxtFld.getText());
	}
}
