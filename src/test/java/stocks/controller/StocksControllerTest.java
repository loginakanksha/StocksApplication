package stocks.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import stocks.exception.ErrorCode;
import stocks.model.StocksRequest;
import stocks.model.StocksResponse;
import stocks.reqHandler.StocksRequestHandler;
import stocks.util.ResponseGenerator;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StocksControllerTest {
	
	@Mock
	private StocksRequestHandler reqHandler;
	
	@InjectMocks
	private StocksController stocksController;
	
	private StocksResponse stocksResponse;
	private ResponseGenerator responseGen;
	private StocksRequest stocksRequest;
	
	private static final int ID =1;
	private static final String NAME = "TEST";
	private static final String TEST_ID = "1";
	private static final String CURRENT_PRICE="100.50";
	
	@Before
	public void setup() {
		responseGen = new ResponseGenerator();
		stocksResponse = responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null);
		stocksRequest = new StocksRequest();
		stocksRequest.setId(TEST_ID);
		stocksRequest.setName(NAME);
		stocksRequest.setPrice(CURRENT_PRICE);
	}
	
	@Test
	public void testGetAllStocksRequest() {
		Mockito.when(reqHandler.handleGetAllStocksRequest()).thenReturn(stocksResponse);
		StocksResponse expectedResponse = responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null);
		StocksResponse actualResponse = stocksController.getStocks();
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testGetStockRequest() {
		Mockito.when(reqHandler.handleGetStockRequest(ID)).thenReturn(stocksResponse);
		StocksResponse expectedResponse = responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null);
		StocksResponse actualResponse = stocksController.getStock(ID);
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testPostStocksRequest() {
		Mockito.when(reqHandler.handlePostRequest(TEST_ID, CURRENT_PRICE)).thenReturn(stocksResponse);
		StocksResponse expectedResponse = responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null);
		StocksResponse actualResponse = stocksController.updateStock(stocksRequest);
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testPutStocksRequest() {
		Mockito.when(reqHandler.handlePutRequest(TEST_ID, NAME, CURRENT_PRICE)).thenReturn(stocksResponse);
		StocksResponse expectedResponse = responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null);
		StocksResponse actualResponse = stocksController.addStock(stocksRequest);
		assertEquals(expectedResponse, actualResponse);
	}

}
