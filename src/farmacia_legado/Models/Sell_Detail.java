package farmacia_legado.Models;

public class Sell_Detail {
	
	private int id;
	private Product product_id;
	private String name;
	private int quantity;
	private float subtotal;
	private int sell_id;
	private float price;
	private float total;
	
	public Sell_Detail(Product product_id, int quantity) {
		this.product_id = product_id;
		this.quantity = quantity;
	}
	
	public Sell_Detail(String name, float price, int quantity, float subtotal, float total) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.total = total;
	}
	
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = product_id.getPrice();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the product_id
	 */
	public String getProduct_id() {
		return product_id.getName();
	}
	
	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}
	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the subtotal
	 */
	public float getSubtotal() {
		return subtotal;
	}
	
	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(int quantity) {
		this.subtotal = product_id.getPrice() * quantity;
	}
	
	/**
	 * @return the sell_id
	 */
	public int getSell_id() {
		return sell_id;
	}
	
	/**
	 * @param sell_id the sell_id to set
	 */
	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}