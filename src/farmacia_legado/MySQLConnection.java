package farmacia_legado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private static Connection connection = null;
	// Credentials for connection to MySQL
	private static String dbName = "db181086";
	private static String user = "root";
	private static String pwd = "";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/" + dbName + "?user=" + user + "&password=" + pwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
