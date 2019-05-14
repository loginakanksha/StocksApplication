package stocks.util;

import org.junit.Before;
import org.junit.Test;

import stocks.exception.ErrorCode;
import stocks.model.StocksResponse;

import static org.junit.Assert.assertEquals;

public class ResponseGeneratorTest {
	
	private ResponseGenerator responseGen;
	
	@Before
	public void setup(){
		responseGen= new ResponseGenerator();
	}
	
	@Test
	public void testResponseGenerator(){
		StocksResponse stocksResponse = responseGen.createResponse(ErrorCode.SA_SUCCESS	, null, null);
		assertEquals(stocksResponse.getResponseCode(), "200");
		assertEquals(stocksResponse.getMessage(), "SUCCESS");
		assertEquals(stocksResponse.getStock(), null);
		assertEquals(stocksResponse.getStocks(), null);
	}

}