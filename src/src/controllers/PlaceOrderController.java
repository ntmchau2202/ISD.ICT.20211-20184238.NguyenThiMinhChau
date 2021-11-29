package controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Order;
import entities.medias.Media;
import exceptions.aims.AIMSException;

/**
 * 
 * @author Hikaru
 *
 */
public class PlaceOrderController extends AIMSBaseController {
	private Order order;
	
	/**
	 * create a controller for handling place order requests
	 */
	public PlaceOrderController() {
		
	}
	
	public boolean validateOrder() throws AIMSException {
		return false;
	}
	
	public boolean validateItemSelection(Media media, int selectedQuantity, int availableQuantity) {
		if(media == null) return false;
		if(selectedQuantity <= 0 || availableQuantity < 0) return false;
		if(selectedQuantity > availableQuantity) return false;
		return true;
	}
}