package entities;
import java.util.HashMap;


import entities.medias.*;
import exceptions.aims.PlaceOrderException;
/**
 * 
 * @author Hikaru
 *
 */
public class RushOrder extends Order {
	/**
	 * 
	 */
	private HashMap<Media, Integer> listRushItem;
	/**
	 * 
	 * @param cart
	 * @throws PlaceOrderException
	 */
	public RushOrder(Cart cart) throws PlaceOrderException {
		super(cart);
		try {
			listRushItem = new HashMap<Media, Integer>();
			for(java.util.Map.Entry<Media, Integer> entry : cart.getListMedia().entrySet()) {
				addItemToRushOrder(entry.getKey(), entry.getValue());
			}			
		} catch (Exception e) {
			throw new PlaceOrderException(e.getMessage());
		}
	}
	/**
	 * 
	 * @param media
	 * @param qty
	 */
	public void addItemToRushOrder(Media media, Integer qty) {
		if(media.isRushSupported()) {
			listRushItem.put(media, qty);
		}
	}
	/**
	 * 
	 */
	public void adjustOrder(Media item, int newQuantity) throws PlaceOrderException {
		try {
			if(newQuantity <= 0) {
				this.listRushItem.remove(item);
			} else {
				this.listRushItem.replace(item, Integer.valueOf(newQuantity));
			}
		} catch (Exception e) {
			throw new PlaceOrderException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<Media, Integer> getListRushItem() {
		return this.listRushItem;
	}
}
