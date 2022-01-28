package controllers;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map.Entry;

import entities.Cart;
import entities.medias.Media;
import exceptions.aims.InvalidMediaInfoException;
import exceptions.aims.MediaUpdateException;

public class CartController {
	private Cart cart;
	private static CartController cartController;
	
	private CartController() {
		cart = new Cart();
	}
	
	public static CartController getCartController() {
		if (cartController == null) {
			cartController = new CartController();
		}
		
		return cartController;
	}
	
	public Cart getCart() {
		return this.cart;
	}
	
	public void removeItemFromCart(Media m) throws MediaUpdateException {
		this.cart.deleteItemFromCart(m);
	}
	
	public void addItemToCart(Media m, int quan) throws MediaUpdateException, InvalidMediaInfoException, SQLException {
		if (quan > InformationController.getInformationController().getMediaCurrentAvailableQuantity(m)) {
			throw new MediaUpdateException("Selected quantity exceed available quantity");
		}
		this.cart.addItemToCart(m, quan);
	}
	
	public void adjustItemQuantity(Media m, int newQuan) throws MediaUpdateException, InvalidMediaInfoException, SQLException {
		if (newQuan > InformationController.getInformationController().getMediaCurrentAvailableQuantity(m)) {
			throw new MediaUpdateException("Selected quantity exceed available quantity");
		}
		this.cart.updateQuantity(m, newQuan);
	}
	
	public double calculateCart() {
		double result = 0;
		Iterator<Entry<Media, Integer>> it = cart.getListMedia().entrySet().iterator();
		try {
			while(it.hasNext()) {
				Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
				result += entry.getKey().getPrice() * entry.getValue();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
