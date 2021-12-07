package entities;

import java.util.HashMap;
import java.util.Map;

import entities.medias.*;
import exceptions.aims.AIMSException;
import exceptions.aims.AIMSUndefinedException;
import exceptions.aims.MediaUpdateException;
import interfaces.PlaceOrderSubsystemInterface;

/**
 * 
 * @author Hikaru
 *
 */
public class Cart {
	private HashMap<Media, Integer> listMedia;
	
	public Cart() {
		listMedia = new HashMap<Media, Integer>();
	}
	
	public HashMap<Media, Integer> getListMedia() {
		return this.listMedia;
	}
	
	public void emptyCart() throws AIMSException {
		try {
			listMedia.clear();
		} catch (Exception e) {
			throw new AIMSUndefinedException(e.getMessage());
		}
	}
	
	public void updateQuantity(Media item, int newQuantity) throws MediaUpdateException {
		try {
			if (listMedia.containsKey(item)) {
				listMedia.replace(item, Integer.valueOf(newQuantity));
			}						
		} catch (Exception e) {
			throw new MediaUpdateException(e.getMessage());
		}
	}
	
	public void addItemToCart(Media item, int quantity) throws MediaUpdateException {
		try {
			listMedia.put(item, Integer.valueOf(quantity));
		} catch (Exception e) {
			throw new MediaUpdateException(e.getMessage());
		}
	}
	
	public void deleteItemFromCart(Media item) throws MediaUpdateException {
		try {
			listMedia.remove(item);
		} catch (Exception e) {
			throw new MediaUpdateException(e.getMessage());
		}
	}
}
