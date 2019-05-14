package stocks.model;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockTest {
	
	private Stock stock;
	
	private static final int ID = 1;
	private static final String NAME = "TEST";
	private static final double CURRENT_PRICE=100.50;
	private static final LocalDateTime TIMESTAMP = LocalDateTime.now();
	
	@Before
	public void setup(){
		stock = new Stock(ID, NAME, CURRENT_PRICE, TIMESTAMP);
		stock.setId(ID);
		stock.setName(NAME);
		stock.setCurrentPrice(CURRENT_PRICE);
		stock.setLastUpdate(TIMESTAMP);
	}
	
	@Test
	public void testStock(){
		assertEquals(ID, stock.getId());
		assertEquals(NAME, stock.getName());
		assertEquals(CURRENT_PRICE, stock.getCurrentPrice(), 2);
		assertEquals(TIMESTAMP, stock.getLastUpdate());
	}

}
