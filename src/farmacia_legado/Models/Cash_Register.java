package farmacia_legado.Models;

import java.sql.Timestamp;

public class Cash_Register {
	
	private int id;
	private float total;
	private boolean status;
	private Timestamp close;
	private Timestamp created;
	
	public Cash_Register(float total, boolean status, Timestamp close) {
		this.total = total;
		this.status = status;
		this.close = close;
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
	public Timestamp getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(Timestamp close) {
		this.close = close;
	}

	/**
	 * @return the created
	 */
	public Timestamp getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}
}