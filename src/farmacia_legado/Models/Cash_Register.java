package farmacia_legado.Models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Cash_Register {
	
	private int id;
	private float total;
	private boolean status;
	private String close;
	private String created;
	
	public Cash_Register(float total, boolean status, Timestamp close) {
		this.total = total;
		this.status = status;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.close = format.format(close);
	}
	
	public Cash_Register(int id, float total, boolean status, Timestamp close, Timestamp created) {
		this.id = id;
		this.total = total;
		this.status = status;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		this.close = format.format(close);
		this.created = format.format(created);
	}
	
	public Cash_Register(int id, float total) {
		this.id = id;
		this.total= total;
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
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the close
	 */
	public String getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(Timestamp close) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.close = format.format(close);
	}

	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Timestamp created) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.created = format.format(created);
	}
}