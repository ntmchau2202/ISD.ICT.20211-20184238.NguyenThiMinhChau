package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Cart;
import entities.Order;
import entities.medias.Media;
import exceptions.aims.AIMSException;
import exceptions.aims.MediaNotAvailableException;
import exceptions.aims.PlaceOrderException;
import utils.DBUtils;

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
	public PlaceOrderController(Cart cart) {
		this.order = new Order(cart);
	}
	
	public boolean validateOrder() throws AIMSException {
		try {
			for (java.util.Map.Entry<Media, Integer> item : this.order.getCart().getListMedia().entrySet()) {
				if(!isItemSelectionValid(item.getKey(), item.getValue())) {
					return false;
				}
			}	
			return true;
		} catch (Exception e) {
			if (e instanceof MediaNotAvailableException) {
				throw e;
			} else {
				throw new PlaceOrderException("undefined exception when placing order: "+e.getMessage());				
			}
		}
	}
	
//	public boolean validateItemSelection(Media media, int selectedQuantity, int availableQuantity) {
//		if(media == null) return false;
//		if(selectedQuantity <= 0 || availableQuantity < 0) return false;
//		if(selectedQuantity > availableQuantity) return false;
//		return true;
//	}
	
	protected boolean isItemSelectionValid(Media media, int selectedQuantity) throws MediaNotAvailableException {
		return (selectedQuantity <= DBUtils.getCurrentAvailableQuatity(media.getTitle()));
	}
}