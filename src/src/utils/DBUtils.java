package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import entities.Customer;
import entities.NormalOrder;
import entities.Order;
import entities.RushOrder;
import entities.medias.*;
import exceptions.aims.InvalidDeliveryException;
import exceptions.aims.InvalidMediaInfoException;

public class DBUtils {
	private static Connection connection;
	private static Connection getDatabase() {
        if (connection != null) return connection;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/lib/aimsdb.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connect database successfully");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return connection;
	}
	
	public static HashMap<Book, Integer> getAllBooks() throws SQLException, InvalidMediaInfoException {
		HashMap<Book,Integer> resultMap = new HashMap<Book,Integer>();
		String stm = "SELECT Media.media_id,"
				+ "Media.media_price,"
				+ "Media.support_rush,"
				+ "Media.media_name,"
				+ "Media.available_quantity,"
				+ "Media.image FROM Media, Book WHERE Media.media_id = Book.media_id";
		PreparedStatement sqlStm = getDatabase().prepareStatement(stm);
		ResultSet result = sqlStm.executeQuery();
		while(result.next()) {
			System.out.println(result.getString("media_name") +"====" + result.getBoolean("support_rush"));
			Book b = new Book(result.getInt("media_id"),
								result.getString("media_name"),
								result.getDouble("media_price"),
								result.getBoolean("support_rush"),
								Configs.ASSET_BOOK_IMG + result.getString("image"));
			String aStm = "Select Author.author_id,"
					+ "Author.author_name FROM MediaAuthor, Author WHERE MediaAuthor.media_id=? AND MediaAuthor.author_id=Author.author_id";
			PreparedStatement aSqlStm = getDatabase().prepareStatement(aStm);
			ResultSet aResult = aSqlStm.executeQuery();
			while(aResult.next()) {
				b.addAuthor(aResult.getString("author_name"));
			}
			resultMap.put(b, result.getInt("available_quantity"));
			
		}
		return resultMap;
	}
	
	public static HashMap<CD, Integer> getAllCD() throws SQLException, InvalidMediaInfoException {
		HashMap<CD,Integer> resultMap = new HashMap<CD,Integer>();
		String stm = "SELECT DISTINCT Media.media_id,"
				+ "Media.media_price,"
				+ "Media.support_rush,"
				+ "Media.media_name,"
				+ "Media.available_quantity,"
				+ "Media.image FROM Media, CD WHERE Media.media_id = CD.media_id";
		PreparedStatement sqlStm = getDatabase().prepareStatement(stm);
		ResultSet result = sqlStm.executeQuery();
		while(result.next()) {
			CD c = new CD(result.getInt("media_id"),
							result.getString("media_name"),
							result.getDouble("media_price"),
							result.getBoolean("support_rush"),
							Configs.ASSET_CD_IMG + result.getString("image"));
			String tStm = "SELECT Track.track_id,"
					+ "Track.track_name,"
					+ "Track.length FROM CD, Track Track WHERE CD.media_id =? AND CD.track_id=Track.track_id";
			PreparedStatement tSqlStm = getDatabase().prepareStatement(tStm);
			tSqlStm.setInt(1, result.getInt("media_id"));
			ResultSet tResult = tSqlStm.executeQuery();
			while(tResult.next()) {
				Track t = new Track(tResult.getInt("track_id"), 
							tResult.getString("track_name"), 
							tResult.getDouble("length"));
				c.addTrack(t);
			}
			System.out.println("CD num of tracks: "+c.getNumOfTracks());
			for (Track t: c.getListTrack()) {
				System.out.println("Track:" + t.getName());
			}
			resultMap.put(c, result.getInt("available_quantity"));
			
		}
		return resultMap;
	}
	
	public static HashMap<DVD, Integer> getAllDVD() throws SQLException, InvalidMediaInfoException {
		HashMap<DVD,Integer> resultMap = new HashMap<DVD,Integer>();
		String stm = "SELECT Media.media_id,"
				+ "Media.media_price,"
				+ "Media.support_rush,"
				+ "Media.media_name,"
				+ "Media.available_quantity,"
				+ "DVD.length,"
				+ "Media.image FROM Media, DVD WHERE Media.media_id = DVD.media_id";
		PreparedStatement sqlStm = getDatabase().prepareStatement(stm);
		ResultSet result = sqlStm.executeQuery();
		while(result.next()) {
			DVD d = new DVD(result.getInt("media_id"),
							result.getString("media_name"),
							result.getDouble("media_price"),
							result.getBoolean("support_rush"),
							Configs.ASSET_DVD_IMG + result.getString("image"));
			d.setLength(result.getDouble("length"));
			resultMap.put(d, result.getInt("available_quantity"));
			
		}
		return resultMap;
	}
	
	public static HashMap<Disc, Integer> getAllDisc() throws InvalidMediaInfoException, SQLException {
		HashMap<Disc, Integer> resultMap = new HashMap<Disc, Integer>();
		HashMap<DVD, Integer> resultDVD = getAllDVD();
		HashMap<CD, Integer> resultCD = getAllCD();
		resultMap.putAll(resultDVD);
		resultMap.putAll(resultCD);
		return resultMap;
	}
	
	public static HashMap<Media, Integer> getAllMedias() throws InvalidMediaInfoException, SQLException {
		HashMap<Media, Integer> resultMap = new HashMap<Media, Integer>();
		HashMap<Book, Integer> resultBook = getAllBooks();
		HashMap<Disc, Integer> resultDisc = getAllDisc();
		resultMap.putAll(resultDisc);
		resultMap.putAll(resultBook);
		return resultMap;
	}
	
	public static HashMap<Media, Integer> getAllSupportRushMedia() throws InvalidMediaInfoException, SQLException {
		HashMap<Media, Integer> resultMap = new HashMap<Media, Integer>();
		HashMap<Media, Integer> allMedias = getAllMedias();
		Iterator<Entry<Media, Integer>> it = allMedias.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
			if (entry.getKey().isRushSupported()) {
				resultMap.put(entry.getKey(), entry.getValue());
			}
		}
		return resultMap;
	}
	
	private static int addCustomer(Customer customer) throws SQLException {
		String stm = "INSERT INTO Customer(customer_name, address, city, phone) VALUES(?,?,?,?)";
		PreparedStatement sqlStm = getDatabase().prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
		sqlStm.setString(1, customer.getCustomerName());
		sqlStm.setString(2, customer.getAddress());
		sqlStm.setString(3, customer.getCity());
		sqlStm.setString(4, customer.getPhone());
		sqlStm.execute();
		ResultSet result = sqlStm.getGeneratedKeys();
		if (result.next()) {
			customer.setCustomerID(result.getInt(1));
			return customer.getCustomerID();
		}
		return -1;
	}
	
	public static void pickMedia(int mediaID, int quantity) throws SQLException {
		String stm = "UPDATE Media SET available_quantity=? WHERE media_id=?";
		PreparedStatement sqlStm = getDatabase().prepareStatement(stm);
		sqlStm.setInt(1, quantity);
		sqlStm.setInt(2, mediaID);
		sqlStm.execute();
	}
	
	public static Customer getCustomerByID(int id) throws SQLException, InvalidDeliveryException {
		String stm = "SELECT * FROM Customer WHERE Customer.customer_id=?";
		PreparedStatement sqlStm = getDatabase().prepareStatement(stm);
		sqlStm.setInt(1, id);
		ResultSet result = sqlStm.executeQuery();
		Customer customer = new Customer(result.getString("customer_name"), result.getString("phone"), result.getString("city"), result.getString("address"));
		customer.setCustomerID(id);
		return customer;
	}
	
	public static void addOrder(Order order) throws SQLException {
		int customerID = addCustomer(order.getCustomer());
		// generate detailID
		String stm = "SELECT count(DISTINCT detail_id) FROM OrderDetails GROUP BY detail_id";
		PreparedStatement sqlStm = getDatabase().prepareStatement(stm);
		ResultSet result = sqlStm.executeQuery();
		int detailID = 0;
		if (!result.next()) {
			detailID = 1;
		} else {
			detailID = result.getInt(1) + 1;
		}
		
		Iterator<Entry<Media, Integer>> it = order.getListItemOrdered().entrySet().iterator();
		while(it.hasNext()) {
			Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
			stm = "INSERT INTO OrderDetails(detail_id, media_id, quantity) VALUES(?,?,?)";
			sqlStm = getDatabase().prepareStatement(stm);
			sqlStm.setInt(1, detailID);
			sqlStm.setInt(2, entry.getKey().getMediaID());
			sqlStm.setInt(3, entry.getValue());
			sqlStm.execute();
		}
		
		stm = "INSERT INTO AIMSOrder(detail_id, customer_id, date_issued, is_rushed) VALUES (	?,?,?,?)";
		sqlStm = getDatabase().prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
//		sqlStm.setInt(1, order.getOrderID());
		sqlStm.setInt(1, detailID);
		sqlStm.setInt(2, customerID);
		sqlStm.setString(3, order.getDateIssued());
		sqlStm.setBoolean(4, order.isRushed());
		sqlStm.execute();
		result = sqlStm.getGeneratedKeys();
		if (result.next()) {
			order.setOrderID(result.getInt(1));
		}
	}
}
