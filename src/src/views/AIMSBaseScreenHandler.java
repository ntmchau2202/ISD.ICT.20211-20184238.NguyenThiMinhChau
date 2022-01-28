package views;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import controllers.AIMSBaseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Configs;

/**
 * This class is the base for all screen handler of the application
 */
public abstract class AIMSBaseScreenHandler {
    protected final Stage stage;
    protected Hashtable<String, String> messages;
    protected FXMLLoader loader;
    protected Pane content;
    private Scene scene;
	protected String screenTitle;
//	protected AIMSBaseController screenController;
	private AIMSBaseScreenHandler previousScreen;
	
	/***
	 * This constructor sets the parent screen of this current screen for future reference if needed
	 * @param prevScreen a handler to the screen that called this screen
	 * @throws IOException 
	 */
	
	protected AIMSBaseScreenHandler(Stage newStage, AIMSBaseScreenHandler prevScreen, String screenPath) throws IOException {
        this.loader = new FXMLLoader(getClass().getResource(screenPath));
        this.loader.setController(this);
        this.previousScreen = prevScreen;
        this.content = (Pane) loader.load();
        this.stage = newStage;
	}
	
	/**
	 * Display a message box with error message and corresponding error code
	 * @param message A detailed message about the error
	 * @param errorCode Code of the error
	 * @throws IOException 
	 */
	public void notifyError(String message) throws IOException {
		PopupScreenHandler handler = new PopupScreenHandler(new Stage(), Configs.ASSET_ICONS + "error.png", message);
		handler.show();
	}
	
	/**
	 * Notify successful transaction of user
	 * @param message detail information about the succeeded action
	 * @throws IOException 
	 */
	public void notifySuccess(String message) throws IOException {
		PopupScreenHandler handler = new PopupScreenHandler(new Stage(), Configs.ASSET_ICONS + "success.png" , message);
		handler.show();
	}
	
	public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public Pane getContent() {
        return this.content;
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }

    public void setImage(ImageView imv, String path) {
        File file = new File(path);
        Image img = new Image(file.toURI().toString());
        imv.setImage(img);
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public void setPreviousScreen(AIMSBaseScreenHandler prev) {
        this.previousScreen = prev;
    }

    public AIMSBaseScreenHandler getPreviousScreen() {
        return this.previousScreen;
    }
    
    public void returnToPreviousScreen() {
    	this.previousScreen.show();
    }
    
    protected abstract void initialize();
}
