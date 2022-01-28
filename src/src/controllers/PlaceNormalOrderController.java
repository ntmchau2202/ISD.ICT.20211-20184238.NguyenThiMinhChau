package controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import boundaries.InterBankBoundary;
import entities.CreditCard;
import entities.Customer;
import entities.NormalOrder;
import entities.Order;
import entities.medias.Media;
import entities.strategies.OrderFeeCalculatorFactory;
import entities.transactions.Transaction;
import exceptions.aims.InvalidDeliveryException;
import exceptions.aims.InvalidMediaInfoException;
import exceptions.aims.MediaUpdateException;
import exceptions.aims.PlaceOrderException;
import exceptions.interbank.InterbankException;
import interfaces.InterbankSubsystemInterface;
import interfaces.OrderCalculatorInterface;
import utils.DBUtils;
import utils.FunctionalUtils;

public class PlaceNormalOrderController extends PlaceOrderController {
	private static PlaceNormalOrderController placeNormalOrderController;
	PlaceNormalOrderController(HashMap<Media, Integer> itemsToBePurchased) throws PlaceOrderException {
		super();
		this.order = new NormalOrder(itemsToBePurchased);
	}
	
	public static PlaceNormalOrderController getPlaceNormalOrderController(HashMap<Media, Integer> itemsToBePurchased) throws PlaceOrderException {
		if (placeNormalOrderController == null) {
			placeNormalOrderController = new PlaceNormalOrderController(itemsToBePurchased);
			placeNormalOrderController.placeOrderController = placeNormalOrderController;
		}
		return placeNormalOrderController;
	}
	
}
