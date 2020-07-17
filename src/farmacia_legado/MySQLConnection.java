package farmacia_legado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import farmacia_legado.Models.Category;
import farmacia_legado.Models.Product;
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
			listCategories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("father_id")));
		}
		return listCategories;
	}
	
	public ArrayList<String> getCategories() throws SQLException {
		connection = getConnection();
		ArrayList<String> listCategories = new ArrayList<>();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name FROM farmacialegado.categories";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listCategories.add(String.valueOf(rs.getInt("id")+" "+rs.getString("name")));
		}
		return listCategories;
	}
	
	public boolean saveCategory(String name, int father_id, String image) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.categories(name, father_id, image, created_at) VALUES"
				+ " ('"+name+"','"+father_id+"','"+image+"','"+created+"')";
		statement.executeUpdate(query);
		return true;
	}
	
	public int editCategoryImage(int pk, String name, int father_id, String image) throws SQLException {
		connection = getConnection();
        String query = "UPDATE farmacialegado.categories SET name=?, father_id=?, image=? WHERE id = ?";
        PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, father_id);
        ps.setString(3, image);
        ps.setInt(4, pk);
        return ps.executeUpdate();
	}
	
	public int editCategory(int pk, String name, int father_id) throws SQLException {
		connection = getConnection();
		String query = "UPDATE farmacialegado.categories SET name=?, father_id=? WHERE id = ?";
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, father_id);
		ps.setInt(3, pk);
		return ps.executeUpdate();
	}
	
	public Category getCategory(int pk) throws SQLException {
		connection = getConnection();
		Category category = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, father_id, image FROM farmacialegado.categories WHERE id = "+pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next()) {
			category = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("father_id"));
			category.setImage(rs.getString("image"));
		}
		return category;
	}
	
	public int destroyCategory(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String query = "DELETE FROM farmacialegado.categories WHERE farmacialegado.categories.id = "+ id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(query);
	}
	
	public ObservableList<Product> indexProducts() throws SQLException {
		connection = getConnection();
		ObservableList<Product> listProducts = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, price, quantify FROM farmacialegado.products";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listProducts.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantify")));
		}
		return listProducts;
	}
	
	public boolean saveProduct(String name, String description, String image, float price, int quantity, int provider, int category) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.products(name, description, image, price, quantify, provider_id, category_id, created_at) VALUES"
				+ " ('"+name+"','"+description+"','"+image+"','"+price+"','"+quantity+"','"+provider+"','"+category+"','"+created+"')";
		statement.executeUpdate(query);
		return true;
	}
	
	public int editProductImage(int pk, String name, String description, float price, int quantity, int provider_id, int category_id, String image) throws SQLException {
		connection = getConnection();
        String query = "UPDATE farmacialegado.products SET name=?, description=?, price=?, quantify=?, provider_id=?, category_id=?, image=? WHERE id = ?";
        PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setFloat(3, price);
        ps.setInt(4, quantity);
        ps.setInt(5, provider_id);
        ps.setInt(6, category_id);
        ps.setString(7, image);
        ps.setInt(8, pk);
        return ps.executeUpdate();
	}
	
	public int editProduct(int pk, String name, String description, float price, int quantity, int provider_id, int category_id) throws SQLException {
		connection = getConnection();
		String query = "UPDATE farmacialegado.products SET name=?, description=?, price=?, quantify=?, provider_id=?, category_id=? WHERE id = ?";
        PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setFloat(3, price);
        ps.setInt(4, quantity);
        ps.setInt(5, provider_id);
        ps.setInt(6, category_id);
        ps.setInt(7, pk);
        return ps.executeUpdate();
	}
	
	public Product getProduct(int pk) throws SQLException {
		connection = getConnection();
		Product product = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, description, image, price, quantify, category_id FROM farmacialegado.products WHERE id = "+pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next()) {
			product = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantify"));
			product.setImage(rs.getString("image"));
			product.setDescription(rs.getString("description"));
			product.setcategory_id(rs.getInt("category_id"));
		}
		return product;
	}
}
