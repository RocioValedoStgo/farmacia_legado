package farmacia_legado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import farmacia_legado.Models.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySQLConnection {
	private static Connection connection = null;
	// Credentials for connection to MySQL
	private static String dbName = "farmacialegado";
	private static String user = "root";
	private static String pwd = "";
	public static String User_fullname;
	public static String User_username;
	public static String User_email;

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
	
	public boolean login(String username, String password) throws SQLException {
		connection = getConnection();
		ResultSet rs;
		String query = "SELECT * FROM farmacialegado.users WHERE username = ? and password = ?";
		PreparedStatement pstatement = (PreparedStatement) connection.prepareStatement(query);
		String pwdEncrypted = encryptedPassword(password);
		pstatement.setString(1, username);
		pstatement.setString(2, pwdEncrypted);
		rs = pstatement.executeQuery();
		if (rs.next()) {
			setUserLogged(rs.getString("name"), rs.getString("last_name"), rs.getString("username"), rs.getString("email"));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean register(String name, String last_name, String username, String phone, String turn, String email, String password) throws SQLException {
		connection = getConnection();
		int rol = 4;
		if (login(username, password) == true) {
			return false;
		} else {
			String pwdEncrypted = encryptedPassword(password);
			Timestamp created = generateTimestamp();
			Statement statement = (Statement) connection.createStatement();
			String query = "INSERT INTO farmacialegado.users(name, last_name, username, phone, turn, rol, email, password, created_at) VALUES"
					+ " ('"+name+"','"+last_name+"','"+username+"','"+phone+"','"+turn+"','"+rol+"','"+email+"','"+pwdEncrypted+"','"+created+"')";
			statement.executeUpdate(query);
			setUserLogged(name, last_name, username, email);
			return true;
		}
	}
	
	private void setUserLogged(String name, String last_name, String username, String email) {
		User_fullname = name + " " + last_name;
		User_username = username;
		User_email = email;
	}
	
	private String encryptedPassword(String password) {
        byte[] palabra = password.getBytes();
        String pwdEncrypt = Base64.getEncoder().encodeToString(palabra);
        return pwdEncrypt;
    }
	
	private Timestamp generateTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return ts;
    }
	
	public ObservableList<Category> indexCategories() throws SQLException {
		connection = getConnection();
		ObservableList<Category> listCategories = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, father_id FROM farmacialegado.categories";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listCategories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("father_id")));
		}
		return listCategories;
	}
	
	public int destroyCategory(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String query = "DELETE FROM farmacialegado.categories WHERE farmacialegado.categories.id = "+ id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(query);
	}
}
