package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import entities.medias.*;
import exceptions.aims.InvalidMediaInfoException;
import utils.DBUtils;
import java.util.Map.Entry;

public class InformationController extends AIMSBaseController {
	private HashMap<Media, Integer> listMedia;
	private HashMap<Book, Integer> listBook;
	private HashMap<CD, Integer> listCD;
	private HashMap<DVD, Integer> listDVD;
	private HashMap<Media, Integer> listSupportRushMedia;
	private static InformationController informationController;
	
	private InformationController() {
		listMedia = new HashMap<Media, Integer>();
		listBook = new HashMap<Book, Integer>();
		listCD = new HashMap<CD, Integer>();
		listDVD = new HashMap<DVD, Integer>();
		listSupportRushMedia = new HashMap<Media, Integer>();
	}
	
	public static InformationController getInformationController() throws InvalidMediaInfoException, SQLException {
		if (informationController == null) {
			informationController = new InformationController();
			informationController.listBook = DBUtils.getAllBooks();
			informationController.listCD = DBUtils.getAllCD();
			informationController.listDVD = DBUtils.getAllDVD();
			informationController.listMedia.putAll(informationController.listBook);
			informationController.listMedia.putAll(informationController.listCD);
			informationController.listMedia.putAll(informationController.listDVD);
			Iterator<Entry<Media, Integer>> it = informationController.listMedia.entrySet().iterator();
			while(it.hasNext()) {
				Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
				if (entry.getKey().isRushSupported()) {
					informationController.listSupportRushMedia.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return informationController;
	}

	public HashMap<Media, Integer> getListMedia() {
		return listMedia;
	}

	public HashMap<Book, Integer> getListBook() {
		return listBook;
	}

	public HashMap<CD, Integer> getListCD() {
		return listCD;
	}

	public HashMap<DVD, Integer> getListDVD() {
		return listDVD;
	}

	public HashMap<Media, Integer> getListSupportRushMedia() {
		return listSupportRushMedia;
	}
	
	public HashMap<Disc, Integer> getListDisc() {
		HashMap<Disc, Integer> result = new HashMap<Disc, Integer>();
		result.putAll(listCD);
		result.putAll(listDVD);
		return result;
	}
	
	public HashMap<Media, Integer> searchMediaByName(String key) {
		HashMap<Media, Integer> result = new HashMap<Media, Integer>();
		Iterator<Entry<Media, Integer>> it = informationController.listMedia.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
			if (entry.getKey().getTitle().toLowerCase().contains(key.toLowerCase())) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}
	
	public Media searchMediaByID(String key) {
		Iterator<Entry<Media, Integer>> it = informationController.listMedia.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
			if (entry.getKey().getMediaID() == Integer.parseInt(key)) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public int getMediaCurrentAvailableQuantity(Media m) {
		return this.listMedia.get(m);
	}
	
	public void pickItemFromStore(Media m, int quantity) throws SQLException {
		if (listMedia.containsKey(m)) {
			int currentQuantity = listMedia.get(m);
			listMedia.replace(m, currentQuantity - quantity);
			if(listCD.containsKey(m)) {
				listCD.replace((CD)m, currentQuantity - quantity);
			}
			
			if (listBook.containsKey(m)) {
				listBook.replace((Book)m, currentQuantity - quantity);
			}
			
			if (listDVD.containsKey(m)) {
				listDVD.replace((DVD)m, currentQuantity - quantity);
			}
			
			if (listSupportRushMedia.containsKey(m)) {
				listSupportRushMedia.replace(m, currentQuantity - quantity);
			}
			
			// update database;
			DBUtils.pickMedia(m.getMediaID(), listMedia.get(m));
		}
	}
}
