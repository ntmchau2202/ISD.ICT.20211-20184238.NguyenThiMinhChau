package entities.medias;

/**
 * 
 * @author Hikaru
 *
 */
public abstract class Media {
	public boolean supportRush;
	
	public Media() {
		
	}
	
	public void setRushSupport(boolean isSupported) {
		this.supportRush = isSupported;
	}
	
	public boolean isRushSupported() {
		return this.supportRush;
	}
}
