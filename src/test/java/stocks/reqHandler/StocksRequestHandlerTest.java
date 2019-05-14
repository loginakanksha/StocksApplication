package stocks.reqHandler;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import stocks.exception.ErrorCode;
import stocks.model.Stock;
import stocks.model.StocksResponse;
import stocks.repository.StocksRepositoryImpl;
import stocks.util.ResponseGenerator;

@RunWith(MockitoJUnitRunner.class)
public class StocksRequestHandlerTest {
	
	private static final int ID = 1;
	private static final String ID_1="ID_1";
	private static final int INVALID_ID = 2;
	private static final String NAME = "TEST";
	private static final double CURRENT_PRICE=100.50;
	private static final String TEST_PRICE="TEST_PRICE";
	private static final double UPDATED_CURRENT_PRICE=200.50;
	private static final LocalDateTime TIMESTAMP = LocalDateTime.now();
	
	@Mock
	private StocksRepositoryImpl repositoryImpl;
	
	@Mock
	private ResponseGenerator responseGen;
	
	@InjectMocks
	private StocksRequestHandler requestHandler;
	
	private Stock stock;
	private List<Stock> stocks;
	private StocksResponse response;
	private ResponseGenerator responseGenerator;
	
	
	@Before
	public void setup(){
		stock = new Stock(ID, NAME, CURRENT_PRICE, TIMESTAMP);
		stocks = new ArrayList<>();
		stocks.add(stock);
		responseGenerator= new ResponseGenerator();
		
	}
	
	@Test
	public void testHandleGetAllStocksRequest(){
		response = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, null, stocks);
		Mockito.when(repositoryImpl.getStockList()).thenReturn(stocks);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_SUCCESS, null, stocks)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, null, stocks);
		StocksResponse actualResponse = requestHandler.handleGetAllStocksRequest();
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandleGetStockRequestSuccessCase(){
		response = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, stock, null);
		Mockito.when(repositoryImpl.getStock(ID)).thenReturn(stock);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_SUCCESS, stock, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, stock, null);
		StocksResponse actualResponse = requestHandler.handleGetStockRequest(ID);
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandleGetStockRequestWithInvalidId(){
		response = responseGenerator.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		Mockito.when(repositoryImpl.getStock(INVALID_ID)).thenReturn(null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		StocksResponse actualResponse = requestHandler.handleGetStockRequest(INVALID_ID);
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePostRequestSuccessCase(){
		response = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, null, null);
		StocksResponse actualResponse = requestHandler.handlePostRequest(String.valueOf(ID), String.valueOf(UPDATED_CURRENT_PRICE));
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePostRequestWithBlankStockId(){
		response = responseGenerator.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null);
		StocksResponse actualResponse = requestHandler.handlePostRequest(null, String.valueOf(UPDATED_CURRENT_PRICE));
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePostRequestWithBlankPrice(){
		response = responseGenerator.createResponse(ErrorCode.SA_BLANK_PRICE, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_BLANK_PRICE, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_BLANK_PRICE, null, null);
		StocksResponse actualResponse = requestHandler.handlePostRequest(String.valueOf(ID), null);
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePostRequestWithInvalidStockIdFormat(){
		response = responseGenerator.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		StocksResponse actualResponse = requestHandler.handlePostRequest(String.valueOf(ID_1), String.valueOf(CURRENT_PRICE));
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePostRequestWithInvalidPriceFormat(){
		response = responseGenerator.createResponse(ErrorCode.SA_INVALID_PRICE, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_INVALID_PRICE, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_INVALID_PRICE, null, null);
		StocksResponse actualResponse = requestHandler.handlePostRequest(String.valueOf(ID), TEST_PRICE);
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePutRequestSuccessCase(){
		response = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_SUCCESS, null, null);
		StocksResponse actualResponse = requestHandler.handlePutRequest(String.valueOf(ID), NAME,String.valueOf(UPDATED_CURRENT_PRICE));
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePutRequestWithBlankStockId(){
		response = responseGenerator.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null);
		StocksResponse actualResponse = requestHandler.handlePutRequest(null, NAME, String.valueOf(UPDATED_CURRENT_PRICE));
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePutRequestWithBlankName(){
		response = responseGenerator.createResponse(ErrorCode.SA_BLANK_NAME, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_BLANK_NAME, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_BLANK_NAME, null, null);
		StocksResponse actualResponse = requestHandler.handlePutRequest(String.valueOf(ID), null, String.valueOf(UPDATED_CURRENT_PRICE));
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePutRequestWithBlankPrice(){
		response = responseGenerator.createResponse(ErrorCode.SA_BLANK_PRICE, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_BLANK_PRICE, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_BLANK_PRICE, null, null);
		StocksResponse actualResponse = requestHandler.handlePutRequest(String.valueOf(ID), NAME, null);
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePutRequestWithInvalidStockIdFormat(){
		response = responseGenerator.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		StocksResponse actualResponse = requestHandler.handlePutRequest(String.valueOf(ID_1), NAME, String.valueOf(CURRENT_PRICE));
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testHandlePutRequestWithInvalidPriceFormat(){
		response = responseGenerator.createResponse(ErrorCode.SA_INVALID_PRICE, null, null);
		Mockito.when(responseGen.createResponse(ErrorCode.SA_INVALID_PRICE, null, null)).thenReturn(response);
		StocksResponse expectedResponse = responseGenerator.createResponse(ErrorCode.SA_INVALID_PRICE, null, null);
		StocksResponse actualResponse = requestHandler.handlePutRequest(String.valueOf(ID), NAME, TEST_PRICE);
		assertEquals(expectedResponse, actualResponse);
	}

}
