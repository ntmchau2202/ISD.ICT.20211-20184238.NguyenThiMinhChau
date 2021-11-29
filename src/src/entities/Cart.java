package entities;

import java.util.HashMap;
import java.util.Map;

import entities.medias.*;

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
}
