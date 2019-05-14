package stocks.model;

import java.io.Serializable;

public class StocksRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4840490527509294541L;
	
	private String id;
	private String name;
	private String currentPrice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentPrice() {
		return currentPrice;
	}
	public void setPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
}
