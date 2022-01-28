package entities.medias;

import interfaces.Playable;

public class Track implements Playable {
	private String name;
	private double length;
	private int ID;
	
	public Track(int ID, String name, double length) {
		this.setName(name);
		this.setID(ID);
		this.setLength(length);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
}
