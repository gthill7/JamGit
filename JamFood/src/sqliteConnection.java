import java.sql.*;
import javax.swing.*;

/**
 * Connect to MySQL database.
 * @author Greyson Hill
 *
 */
public class sqliteConnection {
	protected Connection conn = null;
	
	/**
	 * Connects to restaurants database 
	 * @return OptionPane connection successful or unsuccessful
	 */
	public static Connection dbConnector() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://gcjam.com:3306/jamfood", "greyson", "Theendss147");
			JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
