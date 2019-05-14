package stocks.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StocksRequestTest {
	
	private StocksRequest request;
	
	private static final String ID = "1001";
	private static final String NAME = "TEST";
	private static final String CURRENT_PRICE="100.50";
	
	@Before
	public void setup(){
		request= new StocksRequest();
	}
	
	@Test
	public void testStocksRequest(){
		request.setId(ID);
		request.setName(NAME);
		request.setPrice(CURRENT_PRICE);
		
		assertEquals(ID, request.getId());
		assertEquals(NAME, request.getName());
		assertEquals(CURRENT_PRICE, request.getCurrentPrice());
	}
}
