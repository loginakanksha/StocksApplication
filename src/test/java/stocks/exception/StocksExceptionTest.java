package stocks.exception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StocksExceptionTest {
	
	private StocksException stocksException;
	
	@Before
	public void setup() {
		stocksException = new StocksException(ErrorCode.SA_SUCCESS);
	}
	
	@Test
	public void testStocksException() {
		stocksException.setErrorCode(ErrorCode.SA_SUCCESS.getErrorCode());
		stocksException.setErrorMessage(ErrorCode.SA_SUCCESS.getMessage());
		
		assertEquals(ErrorCode.SA_SUCCESS.getErrorCode(), stocksException.getErrorCode());
		assertEquals(ErrorCode.SA_SUCCESS.getMessage(), stocksException.getErrorMessage());
	}

}
