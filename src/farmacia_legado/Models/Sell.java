package farmacia_legado.Models;

import java.sql.Timestamp;

public class Sell {
	
	private int id;
	private float IVA;
	private float total;
	private float incoming;
	private float output;
	private int cash_id;
	private Timestamp created;
	
	public Sell(int id, float total, int cash_id) {
		this.id = id;
		this.total = total;
		this.cash_id = cash_id;
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
	 * @return the iVA
	 */
	public float getIVA() {
		return IVA;
	}
	/**
	 * @param iVA the iVA to set
	 */
	public void setIVA(float iVA) {
		IVA = iVA;
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
	 * @return the incoming
	 */
	public float getIncoming() {
		return incoming;
	}
	/**
	 * @param incoming the incoming to set
	 */
	public void setIncoming(float incoming) {
		this.incoming = incoming;
	}
	/**
	 * @return the output
	 */
	public float getOutput() {
		return output;
	}
	/**
	 * @param output the output to set
	 */
	public void setOutput(float output) {
		this.output = output;
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

	/**
	 * @return the cash_id
	 */
	public int getCash_id() {
		return cash_id;
	}

	/**
	 * @param cash_id the cash_id to set
	 */
	public void setCash_id(int cash_id) {
		this.cash_id = cash_id;
	}
}