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
	/**
	 * 
	 */
	private HashMap<Media, Integer> listMedia;
	/**
	 * 
	 */
	public Cart() {
		listMedia = new HashMap<Media, Integer>();
	}
	/**
	 * 
	 * @return
	 */
	public HashMap<Media, Integer> getListMedia() {
		return this.listMedia;
	}
	/**
	 * 
	 * @throws AIMSException
	 */
	public void emptyCart() throws AIMSException {
		try {
			listMedia.clear();
		} catch (Exception e) {
			throw new AIMSUndefinedException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param item
	 * @param newQuantity
	 * @throws MediaUpdateException
	 */
	public void updateQuantity(Media item, int newQuantity) throws MediaUpdateException {
		try {
			if (listMedia.containsKey(item)) {
				listMedia.replace(item, Integer.valueOf(newQuantity));
			}						
		} catch (Exception e) {
			throw new MediaUpdateException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param item
	 * @param quantity
	 * @throws MediaUpdateException
	 */
	public void addItemToCart(Media item, int quantity) throws MediaUpdateException {
		try {
			if (listMedia.containsKey(item)) {
				int currentQuantity = listMedia.get(item);
				int newQuantity = currentQuantity + quantity;
				listMedia.replace(item, newQuantity);
			} else {				
				listMedia.put(item, Integer.valueOf(quantity));
			}
		} catch (Exception e) {
			throw new MediaUpdateException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param item
	 * @throws MediaUpdateException
	 */
	public void deleteItemFromCart(Media item) throws MediaUpdateException {
		try {
			listMedia.remove(item);
		} catch (Exception e) {
			throw new MediaUpdateException(e.getMessage());
		}
	}
}
