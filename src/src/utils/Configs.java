package utils;

/**
 * 
 * @author Hikaru
 *
 */
public class Configs {
	/**
	 * Card number
	 */
	public static String CREDIT_CARD_CODE = "126983_20184238_2021";
	// api constants
	public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
	public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";
	/**
	 * Security code of the credit card
	 */
	public static String SECURITY_CODE = "963";
	
	public static final String SCREEN_PATH_SPLASH = "/views/fxml/FXML_SplashScreen.fxml";
	public static final String SCREEN_PATH_MAIN = "/views/fxml/FXML_MainScreen.fxml";
	public static final String SCREEN_PATH_CART = "/views/fxml/FXML_CartScreen.fxml";
	public static final String SCREEN_PATH_NORMAL_DELIVERY = "/views/fxml/FXML_DeliveryScreen.fxml";
	public static final String SCREEN_PATH_RUSH_DELIVERY = "/views/fxml/FXML_RushDeliveryScreen.fxml";
	public static final String SCREEN_PATH_INVOICE = "/views/fxml/FXML_InvoiceScreen.fxml";
	public static final String SCREEN_PATH_PAYMENT = "/views/fxml/FXML_PaymentScreen.fxml";
	public static final String SCREEN_PATH_PAYMENT_NOTI = "/views/fxml/FXML_PaymentNotificationScreen.fxml";
	public static final String SCREEN_PATH_RUSH_ORDER = "/views/fxml/FXML_RushOrderScreen.fxml";
	public static final String SCREEN_PATH_RUSH_ORDER_INVOICE = "/views/fxml/FXML_RushOrderInvoiceScreen.fxml";
	public static final String SCREEN_PATH_ITEM_IN_STORE = "/views/fxml/FXML_ItemInStoreScreen.fxml";
	public static final String SCREEN_PATH_ITEM_IN_CART = "/views/fxml/FXML_ItemInCartScreen.fxml";
	public static final String SCREEN_PATH_ITEM_DETAILS = "/views/fxml/FXML_ViewItemScreen.fxml";
	public static final String SCREEN_PATH_ITEM_IN_ORDER = "/views/fxml/FXML_ItemInOrderScreen.fxml";
	public static final String SCREEN_PATH_POPUP = "/views/fxml/FXML_Popup.fxml";
	

	public static final String ASSET_BOOK_IMG = "assets/books/";
	public static final String ASSET_DVD_IMG = "assets/dvds/";
	public static final String ASSET_CD_IMG = "assets/cds/";
	public static final String ASSET_ICONS = "src/assets/icons/";
	public static final String CURRENCY = " VND";
	public static final String TIME_UNIT = " min(s)";
	
	public static final double vatPercentage = 0.1;
	public static final double priceFreeShipping = 500000;
	public static final double HNHCMShippingFee = 22000;
	public static final double otherShippingFee = 30000;
	public static final double additionalShippingFee = 2500;
	public static final double additonalRushShippingFee = 10000;
	
}
