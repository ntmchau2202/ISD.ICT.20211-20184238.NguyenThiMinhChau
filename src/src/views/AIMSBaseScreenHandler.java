package views;

import controllers.AIMSBaseController;

/**
 * This class is the base for all screen handler of the application
 */
public abstract class AIMSBaseScreenHandler {
	protected String screenTitle;
	protected AIMSBaseController screenController;
	private AIMSBaseScreenHandler previousScreen;
	
	/***
	 * This constructor sets the parent screen of this current screen for future reference if needed
	 * @param prevScreen a handler to the screen that called this screen
	 */
	
	protected AIMSBaseScreenHandler(AIMSBaseScreenHandler prevScreen) {
		this.previousScreen = prevScreen;
	}
	
	/**
	 * Display a message box with error message and corresponding error code
	 * @param message A detailed message about the error
	 * @param errorCode Code of the error
	 */
	public void notifyError(String message, int errorCode) {
		
	}
	
	/**
	 * Notify successful transaction of user
	 * @param message detail information about the succeeded action
	 */
	public void notifySuccess(String message) {
		
	}
	
	/***
	 * Initialize the components of each specific screen and display it on the stage
	 */
	protected abstract void initialize();
}
