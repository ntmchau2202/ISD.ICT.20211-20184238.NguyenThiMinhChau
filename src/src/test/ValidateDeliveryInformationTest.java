package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controllers.DeliveryController;

class ValidateDeliveryInformationTest {
	
	private DeliveryController deliveryController;

	@BeforeEach
	void setUp() throws Exception {
		deliveryController = new DeliveryController();
	}

	@ParameterizedTest
	@CsvSource({
		"0123456789,true",
		"0123,false",
		"123abc,false",
		"1234567890,false"
	})
	void testValidatePhoneNumber(String phone, boolean expected) {
		boolean isValidated = deliveryController.validatePhoneNumber(phone);
		assertEquals(expected, isValidated);
	}
	
	@ParameterizedTest
	@CsvSource({
		"nguyenlm,true",
		"nguyen123,false",
		"S#,false",
		" ,false"
	})
	void testValidateName(String name, boolean expected) {
		assertEquals(expected, deliveryController.validateName(name));
	}
	
	@ParameterizedTest
	@CsvSource({
		"hanoi,true",
		"ha noi,true",
		"15 hai ba trung,true",
		" ,false",
	})
	void testValidateAddress(String address, boolean expected) {
		assertEquals(expected, deliveryController.validateAddress(address));
	}
	
}
