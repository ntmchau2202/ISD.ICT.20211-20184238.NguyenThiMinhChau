package views;

import java.io.IOException;
import java.util.ArrayList;

import controllers.PlaceNormalOrderController;
import exceptions.aims.InvalidDeliveryException;
import exceptions.aims.PlaceOrderException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;

public class NormalDeliveryScreenHandler extends AIMSBaseScreenHandler {
	@FXML
	TextField nameTxtFld;
	@FXML
	TextField phoneTxtFld;
	@FXML
	TextField addressTxtFld;
	@FXML
	TextField instructionTxtFld;
	@FXML
	ChoiceBox<String> cityChoiceBox;
	@FXML
	CheckBox selectRushCheckBox;
	@FXML 
	Button submitBtn;
	@FXML 
	ImageView backBtn;
	
	ArrayList<String> choices = new ArrayList<String>();
	protected NormalDeliveryScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, String path)
			throws IOException {
		super(newStage, prevScreen, path);
		choices.add("Hanoi");
		choices.add("Tp. HCM");
		initialize();
	}

	@Override
	protected void initialize() {
		cityChoiceBox.setItems(FXCollections.observableArrayList(choices));
		submitBtn.setOnMouseClicked(e->{
			try {
				validateInput();
				// TODO: AIMS: support rush order here
				PlaceNormalOrderController.getPlaceNormalOrderController(null).addCustomer(nameTxtFld.getText(), cityChoiceBox.getValue().toString(), addressTxtFld.getText(), phoneTxtFld.getText());
				PlaceNormalOrderController.getPlaceNormalOrderController(null).addDeliveryInstruction(instructionTxtFld.getText());
				InvoiceScreenHandler invoiceScreen = new InvoiceScreenHandler(this.stage, this, PlaceNormalOrderController.getPlaceNormalOrderController(null).getOrder());
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
		
		PlaceNormalOrderController.getPlaceNormalOrderController(null).validateOrderInformation(nameTxtFld.getText(), phoneTxtFld.getText(), addressTxtFld.getText());
	}
}
