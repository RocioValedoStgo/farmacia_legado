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
import farmacia_legado.Models.Cash_Register;
import farmacia_legado.Models.Category;
import farmacia_legado.Models.Product;
import farmacia_legado.Models.Provider;
import farmacia_legado.Models.Sell;
import farmacia_legado.Models.Sell_Detail;
import farmacia_legado.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySQLConnection {
	private static Connection connection = null;
	public static String User_fullname;
	public static String User_username;
	public static String User_email;
	public static int User_rol;
	// Credentials for connection to MySQL
	private static String dbName = "farmacialegado";
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
			setUserLogged(rs.getString("name"), rs.getString("last_name"), rs.getString("username"),
					rs.getString("email"), rs.getInt("rol"));
			return true;
		} else {
			return false;
		}
	}

	public boolean register(boolean action, String name, String last_name, String username, String phone, String turn,
			int rol, String email, String password) throws SQLException {
		connection = getConnection();
		int auxRol = 3;
		if (login(username, password)) {
			return false;
		} else {
			String pwdEncrypted = encryptedPassword(password);
			Timestamp created = generateTimestamp();
			Statement statement = (Statement) connection.createStatement();
			String query = "INSERT INTO farmacialegado.users(name, last_name, username, phone, turn, rol, email, password, created_at) VALUES"
					+ " ('" + name + "','" + last_name + "','" + username + "','" + phone + "','" + turn + "','"
					+ auxRol + "','" + email + "','" + pwdEncrypted + "','" + created + "')";
			statement.executeUpdate(query);
			if (action) {
				setUserLogged(name, last_name, username, email, 3);
			}
			return true;
		}
	}

	public ObservableList<User> indexUsers() throws SQLException {
		connection = getConnection();
		ObservableList<User> listUsers = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, last_name, turn, rol FROM farmacialegado.users WHERE rol != 1";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listUsers.add(
					new User(rs.getInt("ID"), String.valueOf(rs.getString("name") + " " + rs.getString("last_name")),
							rs.getString("turn"), rs.getInt("rol")));
		}
		return listUsers;
	}

	public int editUser(int pk, String name, String last_name, String username, String phone, String email, String turn)
			throws SQLException {
		connection = getConnection();
		String query = "UPDATE farmacialegado.users SET name=?, last_name=?, username=?, phone=?, email=?, turn=? WHERE id = ?";
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, last_name);
		ps.setString(3, username);
		ps.setString(4, phone);
		ps.setString(5, email);
		ps.setString(6, turn);
		ps.setInt(7, pk);
		return ps.executeUpdate();
	}

	public int editRolUser(int pk, int rol) throws SQLException {
		connection = getConnection();
		String query = "UPDATE farmacialegado.users SET rol = ? WHERE id = ?";
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
		ps.setInt(1, rol);
		ps.setInt(2, pk);
		return ps.executeUpdate();
	}
	
	public int editPwdUser(int pk, String newPwd) throws SQLException {
		connection = getConnection();
		String pwdEncrypted = encryptedPassword(newPwd);
		String query = "UPDATE farmacialegado.users SET password = ? WHERE id = ?";
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
		ps.setString(1, pwdEncrypted);
		ps.setInt(2, pk);
		return ps.executeUpdate();
	}

	public User getUser(int pk) throws SQLException {
		connection = getConnection();
		User user = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, last_name, username, phone, email, turn, rol FROM farmacialegado.users WHERE id = "
				+ pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next()) {
			user = new User(rs.getInt("id"), String.valueOf(rs.getString("name") + " " + rs.getString("last_name")),
					rs.getString("turn"), rs.getInt("rol"));
			user.setName(rs.getString("name"));
			user.setLast_name(rs.getString("last_name"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
		}
		return user;
	}

	public int destroyUser(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String query = "DELETE FROM farmacialegado.users WHERE farmacialegado.users.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(query);
	}

	private void setUserLogged(String name, String last_name, String username, String email, int rol) {
		User_fullname = name + " " + last_name;
		User_username = username;
		User_email = email;
		User_rol = rol;
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
			listCategories.add(String.valueOf(rs.getInt("id") + " " + rs.getString("name")));
		}
		return listCategories;
	}

	public boolean saveCategory(String name, int father_id, String image) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.categories(name, father_id, image, created_at) VALUES" + " ('" + name
				+ "','" + father_id + "','" + image + "','" + created + "')";
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
		String query = "SELECT id, name, father_id, image FROM farmacialegado.categories WHERE id = " + pk;
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
		String query = "DELETE FROM farmacialegado.categories WHERE farmacialegado.categories.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(query);
	}

	public ObservableList<Provider> indexProviders() throws SQLException {
		connection = getConnection();
		ObservableList<Provider> listProviders = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, phone, address, email FROM farmacialegado.providers";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listProviders.add(new Provider(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
					rs.getString("email"), rs.getString("phone")));
		}
		return listProviders;
	}

	public ArrayList<String> getProviders() throws SQLException {
		connection = getConnection();
		ArrayList<String> listProviders = new ArrayList<>();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name FROM farmacialegado.providers";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listProviders.add(String.valueOf(rs.getInt("id") + " " + rs.getString("name")));
		}
		return listProviders;
	}

	public boolean saveProvider(String name, String address, String phone, String email) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.providers(name, address, phone, email, created_at) VALUES" + " ('"
				+ name + "','" + address + "','" + phone + "','" + email + "','" + created + "')";
		statement.executeUpdate(query);
		return true;
	}

	public Provider getProvider(int pk) throws SQLException {
		connection = getConnection();
		Provider provider = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, address, phone, email FROM farmacialegado.providers WHERE id = " + pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next()) {
			provider = new Provider(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
					rs.getString("email"), rs.getString("phone"));
		}
		return provider;
	}

	public int editProvider(int pk, String name, String address, String phone, String email) throws SQLException {
		connection = getConnection();
		String query = "UPDATE farmacialegado.providers SET name=?, address=?, phone=?, email=? WHERE id = ?";
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, address);
		ps.setString(3, phone);
		ps.setString(4, email);
		ps.setInt(5, pk);
		return ps.executeUpdate();
	}

	public int destroyProvider(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String query = "DELETE FROM farmacialegado.providers WHERE farmacialegado.providers.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(query);
	}

	public ObservableList<Product> indexProducts() throws SQLException {
		connection = getConnection();
		ObservableList<Product> listProducts = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, price, quantity FROM farmacialegado.products";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listProducts.add(
					new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity")));
		}
		return listProducts;
	}

	public ArrayList<String> getProducts() throws SQLException {
		connection = getConnection();
		ArrayList<String> products = new ArrayList<>();
		ResultSet rs;
		Statement statement;
		String query = "SELECT name FROM farmacialegado.products";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			products.add(rs.getString("name"));
		}
		return products;
	}

	public Product getProduct(String name) throws SQLException {
		connection = getConnection();
		Product product = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, price, quantity FROM farmacialegado.products WHERE name = '" + name + "'";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next())
			product = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
		return product;
	}

	public boolean saveProduct(String name, String description, String image, float price, int quantity, int provider,
			int category) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.products(name, description, image, price, quantity, provider_id, category_id, created_at) VALUES"
				+ " ('" + name + "','" + description + "','" + image + "','" + price + "','" + quantity + "','"
				+ provider + "','" + category + "','" + created + "')";
		statement.executeUpdate(query);
		return true;
	}

	public int editProductImage(int pk, String name, String description, float price, int quantity, int provider_id,
			int category_id, String image) throws SQLException {
		connection = getConnection();
		String query = "UPDATE farmacialegado.products SET name=?, description=?, price=?, quantity=?, provider_id=?, category_id=?, image=? WHERE id = ?";
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

	public int editProduct(int pk, String name, String description, float price, int quantity, int provider_id,
			int category_id) throws SQLException {
		connection = getConnection();
		String query = "UPDATE farmacialegado.products SET name=?, description=?, price=?, quantity=?, provider_id=?, category_id=? WHERE id = ?";
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
		String query = "SELECT id, name, description, image, price, quantity, category_id, provider_id FROM farmacialegado.products WHERE id = "
				+ pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next()) {
			product = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
			product.setImage(rs.getString("image"));
			product.setDescription(rs.getString("description"));
			product.setcategory_id(rs.getInt("category_id"));
			product.setprovider_id(rs.getInt("provider_id"));
		}
		return product;
	}

	public int destroyProduct(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String query = "DELETE FROM farmacialegado.products WHERE farmacialegado.products.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(query);
	}

	public boolean saveDetails(int product_id, int quantity, float subtotal, int sell_id) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.sell_details(product_id, quantity, subtotal, sell_id, created_at) VALUES"
				+ " ('" + product_id + "','" + quantity + "','" + subtotal + "','" + sell_id + "','" + created + "')";
		statement.executeUpdate(query);
		return true;
	}

	public int saveSell(float total, float money, float change, int cash_id) throws SQLException {
		connection = getConnection();
		float IVA = Float.parseFloat("16");
		Timestamp created = generateTimestamp();
		ResultSet rs;
		int result = 0;
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.sells(IVA, total, incoming, output, cash_register_id, created_at) VALUES ('"
				+ IVA + "','" + total + "','" + money + "','" + change + "','" + cash_id + "','" + created + "')";
		result = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		if (result == 0)
			throw new SQLException("No se pudo guardar");
		rs = statement.getGeneratedKeys();
		if (rs.next())
			result = rs.getInt(1);
		return result;
	}
	
	public boolean saveSellZero(float total, float money, float change, int cash_id) throws SQLException {
		connection = getConnection();
		float IVA = Float.parseFloat("16");
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO farmacialegado.sells(IVA, total, incoming, output, cash_register_id, created_at) VALUES ('"
				+ IVA + "','" + total + "','" + money + "','" + change + "','" + cash_id + "','" + created + "')";
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		return true;
	}

	public ObservableList<Sell_Detail> indexSell(int pkSell) throws SQLException {
		connection = getConnection();
		ObservableList<Sell_Detail> listDetails = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT products.name, products.price, details.quantity, details.subtotal, sells.total FROM sell_details as details\r\n"
				+ "JOIN sells\r\n" + "JOIN products\r\n" + "WHERE details.sell_id = sells.id and sells.id = " + pkSell
				+ " and details.product_id = products.id ORDER BY details.id DESC";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listDetails.add(new Sell_Detail(rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"),
					rs.getFloat("subtotal"), rs.getFloat("total")));
		}
		return listDetails;
	}

	public ObservableList<Sell> indexSells() throws SQLException {
		connection = getConnection();
		ObservableList<Sell> listSells = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, total, cash_register_id FROM sells ORDER BY created_at DESC";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next())
			listSells.add(new Sell(rs.getInt("id"), rs.getFloat("total"), rs.getInt("cash_register_id")));
		return listSells;
	}

	public ObservableList<Sell> getSells(int pk) throws SQLException {
		connection = getConnection();
		ObservableList<Sell> listSells = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT sells.id, sells.total, sells.created_at FROM cash_register\r\n" + "JOIN sells\r\n"
				+ "WHERE sells.cash_register_id = cash_register.id and cash_register.id = " + pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next())
			listSells.add(new Sell(rs.getInt("id"), rs.getFloat("total"), rs.getInt("created_at")));
		return listSells;
	}

	public ObservableList<Cash_Register> indexCashs() throws SQLException {
		connection = getConnection();
		ObservableList<Cash_Register> listCashs = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, total, status, close, created_at FROM cash_register WHERE status = 0 ORDER BY id DESC";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next())
			listCashs.add(new Cash_Register(rs.getInt("id"), rs.getFloat("total"), rs.getBoolean("status"),
					rs.getTimestamp("close"), rs.getTimestamp("created_at")));
		return listCashs;
	}

	public int saveCashRegister() throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		ResultSet rs;
		int result = 0;
		Statement statement;
		String query = "SELECT id FROM cash_register WHERE `status` = 0 ORDER BY created_at DESC limit 1";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next())
			result = rs.getInt(1);
		else {
			query = "INSERT INTO cash_register(total, status, created_at) VALUES ('" + 0 + "','" + 0 + "','" + created
					+ "')";
			result = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			if (result == 0)
				throw new SQLException("No se pudo guardar");
			rs = statement.getGeneratedKeys();
			if (rs.next())
				result = rs.getInt(1);
		}
		return result;
	}

	public int updateTotalCash(int id, float total) throws SQLException {
		connection = getConnection();
		String query = "UPDATE cash_register SET total=? WHERE id = ?";
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
		ps.setFloat(1, total);
		ps.setInt(2, id);
		return ps.executeUpdate();
	}

	public Cash_Register getCashRegister() throws SQLException {
		connection = getConnection();
		Cash_Register cash = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, total, status, created_at FROM cash_register WHERE `status` = 1 ORDER BY created_at DESC LIMIT 1";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next())
			cash = new Cash_Register(rs.getInt("id"), rs.getFloat("total"));
		return cash;
	}

	public int getCashActive() throws SQLException {
		connection = getConnection();
		int result = 0;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, created_at FROM cash_register WHERE status = 1 ORDER BY created_at DESC LIMIT 1";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next())
			result = rs.getInt("id");
		return result;
	}

	public int closeCashRegister(int pkCash) throws SQLException {
		connection = getConnection();
		Timestamp now = generateTimestamp();
		String query = "UPDATE cash_register SET status=?, close=? WHERE id=?";
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
		ps.setInt(1, 0);
		ps.setTimestamp(2, now);
		ps.setInt(3, pkCash);
		return ps.executeUpdate();
	}

	public boolean newCashRegister() throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String query = "INSERT INTO cash_register(total, status, created_at) VALUES" + " ('" + 0.0 + "','" + 1 + "','"
				+ created + "')";
		statement.executeUpdate(query);
		return true;
	}

	public boolean checkSellZero(String now) throws SQLException {
		connection = getConnection();
		ResultSet rs;
		Statement statement;
		String query = "SELECT created_at FROM sells WHERE created_at LIKE '" + now.substring(0, 10)
				+ "%' ORDER BY created_at DESC LIMIT 1";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next())
			return true;
		else
			return false;
	}
}