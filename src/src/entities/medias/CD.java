package entities.medias;

import java.util.ArrayList;

import exceptions.aims.InvalidMediaInfoException;
import interfaces.Playable;

/**
 * 
 * @author Hikaru
 *
 */
public class CD extends Disc implements Playable {
	private ArrayList<Track> listTrack;
	public CD(int ID, String title, double price, boolean supportRush, String imagePath) throws InvalidMediaInfoException {
		super(ID, title, price, supportRush, imagePath);
		listTrack = new ArrayList<Track>();
	}

	public CD(int ID, String title, double price, boolean supportRush, String imagePath, ArrayList<Track> listTrack) throws InvalidMediaInfoException {
		super(ID, title, price, supportRush, imagePath);
		this.listTrack = listTrack;
		updateLength();
	}
	
	public void addTrack(Track track) {
		if (!this.listTrack.contains(track)) {
			this.listTrack.add(track);
		}
		updateLength();
	}
	
	public void removeTrack(Track track) {
		if (this.listTrack.contains(track)) {
			this.listTrack.remove(track);
		}
		updateLength();
	}
	
	public ArrayList<Track> getListTrack() {
		return this.listTrack;
	}
	
	private void updateLength() {
		this.length = 0;
		for (Track t : this.listTrack) {
			length += t.getLength();
		}
	}
	
	public int getNumOfTracks() {
		return this.listTrack.size();
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
}
