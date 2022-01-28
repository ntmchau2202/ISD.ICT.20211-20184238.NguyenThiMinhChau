package entities;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import entities.medias.Media;
import exceptions.aims.PlaceOrderException;

/**
 * 
 * @author Hikaru
 *
 */
public class NormalOrder extends Order{
	public boolean isRushed() {
		return false;
	}
	
	public NormalOrder() {
		super();
	}
	
	public NormalOrder(HashMap<Media,Integer> listItem) {
		super(listItem);
	}
}
