package stocks.model;

import org.junit.Before;
import org.junit.Test;

import stocks.exception.ErrorCode;
import static org.junit.Assert.assertEquals;
public class StocksResponseTest {
	
	private StocksResponse stocksResponse;
	
	@Before
	public void setup() {
		stocksResponse = new StocksResponse();
		stocksResponse.setResponseCode(ErrorCode.SA_SUCCESS.getErrorCode());
		stocksResponse.setMessage(ErrorCode.SA_SUCCESS.getMessage());
		stocksResponse.setStock(null);
		stocksResponse.setStocks(null);
	}
	
	@Test
	public void testStocksResponse() {
		assertEquals(ErrorCode.SA_SUCCESS.getErrorCode(), stocksResponse.getResponseCode());
		assertEquals(ErrorCode.SA_SUCCESS.getMessage(), stocksResponse.getMessage());
		assertEquals(null, stocksResponse.getStock());
		assertEquals(null, stocksResponse.getStocks());
	}
	
	@Test
	public void testStocksResponseEquals(){
		StocksResponse stocksResponse2 = new StocksResponse();
		stocksResponse2.setResponseCode(ErrorCode.SA_SUCCESS.getErrorCode());
		stocksResponse2.setMessage(ErrorCode.SA_SUCCESS.getMessage());
		stocksResponse2.setStock(null);
		stocksResponse2.setStocks(null);
		assertEquals(stocksResponse.hashCode(), stocksResponse2.hashCode());
		assertEquals(stocksResponse, stocksResponse2);
	}

}
