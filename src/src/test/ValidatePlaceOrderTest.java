package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controllers.PlaceNormalOrderController;
import controllers.RushOrderController;
import entities.medias.Book;

class ValidatePlaceOrderTest {
	PlaceNormalOrderController placeOrderController;
	RushOrderController rushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceNormalOrderController();
		rushOrderController = new RushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"Cheems, 10, 11, true",
		"Cheems, 11, 11, true",
		"Cheems, 12, 11, false",
		"Cheems, 0, 11, false",
		"Cheems, 12, 0, false",
		"Cheems, 0, 0, false",
		"Cheems, -1, 10, false",
		"Cheems, 10, -1, false",
		" , 12, 11, false",
	})
	void testNormalOrderItemSelection(String name, int selectedQty, int availableQty, boolean desiredOutput) {
		Book book = new Book();
		if(name == null) book = null;
		assertEquals(desiredOutput, placeOrderController.validateItemSelection(book, selectedQty, availableQty));
	}

	@ParameterizedTest
	@CsvSource({
		"Cheems, true, 10, 11, true",
		"Cheems, true, 11, 11, true",
		"Cheems, true, 12, 11, false",
		"Cheems, true, 0, 11, false",
		"Cheems, true, 12, 0, false",
		"Cheems, true, 0, 0, false",
		"Cheems, true, 0, 0, false",
		"Cheems, true, -1, 10, false",
		"Cheems, true, 10, -1, false",
		" , true, 12, 11, false",
		"Cheems, false, 10, 11, false",
		"Cheems, false, 11, 11, false",
		"Cheems, false, 12, 11, false",
		"Cheems, false, 0, 11, false",
		"Cheems, false, 12, 0, false",
		"Cheems, false, 0, 0, false",
		"Cheems, false, -1, 10, false",
		"Cheems, false, 10, -1, false",
		" , false, 12, 11, false",
	})
	void testNormalOrderItemSelection(String name, boolean isRushSupported, int selectedQty, int availableQty, boolean desiredOutput) {
		Book book = new Book();
		if(name == null) book = null;
		else book.setRushSupport(isRushSupported);
		assertEquals(desiredOutput, rushOrderController.validateItemSelection(book, selectedQty, availableQty));
	}
}
