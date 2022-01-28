package entities.medias;

import java.util.ArrayList;

import exceptions.aims.InvalidMediaInfoException;

/**
 * 
 * @author Hikaru
 *
 */

public class Book extends Media {
	/**
	 * 
	 * @param title
	 * @param price
	 * @param supportRush
	 * @throws InvalidMediaInfoException
	 */
	private ArrayList<String> listAuthor;	
	public Book(int ID, String title, double price, boolean supportRush, String imagePath) throws InvalidMediaInfoException {
		super(ID, title, price, supportRush, imagePath);
		listAuthor = new ArrayList<String>();
	}
	
	public Book(int ID, String title, double price, boolean supportRush, String imagePath, ArrayList<String> authors) throws InvalidMediaInfoException {
		super(ID, title, price, supportRush, imagePath);
		this.listAuthor = authors;
	}
	
	public void addAuthor(String newAuthor) {
		if (!listAuthor.contains(newAuthor)) {
			this.listAuthor.add(newAuthor);
		}
	}
	public void removeAuthor(String author) {
		if(listAuthor.contains(author)) {
			this.listAuthor.remove(author);
		}
	}
	
	public ArrayList<String> getListAuthor() {
		return this.listAuthor;
	}
}
