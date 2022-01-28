package entities;
import java.util.Calendar;
import java.util.HashMap;


import entities.medias.*;
import exceptions.aims.PlaceOrderException;
/**
 * 
 * @author Hikaru
 *
 */
public class RushOrder extends Order {
	public boolean isRushed() {
		return true;
	}
	
	public RushOrder() {
		super();
	}
	
	public RushOrder(HashMap<Media,Integer> listItem) {
		super(listItem);
	}
}
