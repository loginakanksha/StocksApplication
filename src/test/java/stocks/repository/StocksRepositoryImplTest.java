package stocks.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import stocks.configuration.ApplicationConfiguration;
import stocks.exception.StocksException;
import stocks.model.Stock;

@RunWith(MockitoJUnitRunner.class)
public class StocksRepositoryImplTest {
	
	@Mock
	private ApplicationConfiguration configuration;
	
	@InjectMocks
	private StocksRepositoryImpl repositoryImpl;
	private Map<Integer, String> stocks;
	private Stock stock;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	
	private static final int ID=1001;
	private static final int ID2=1002;
	private static final String TEST_STOCK_STRING="1001, Wells Fargo, 200, 2019-05-12 17:23:37.368";
	private static final String STOCK_NAME="Wells Fargo";
	private static final String STOCK_NAME2="TEST";
	private static final double PRICE_200 = 200.0;
	private static final double PRICE_300 = 300.0;
	
	@Before
	public void setup() {
		stocks = new HashMap<>();
		stocks.put(ID, TEST_STOCK_STRING);
		stock = new Stock(ID, STOCK_NAME, PRICE_200, LocalDateTime.parse("2019-05-12 17:23:37.368", formatter));
		Mockito.when(configuration.getStocks()).thenReturn(stocks);
		repositoryImpl.init();
	}
	
	@Test
	public void testGetAllStocks() {
		List<Stock> expectedResponse = new ArrayList<>();
		expectedResponse.add(stock);
		List<Stock> actualResponse = repositoryImpl.getStockList();
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	public void testGetStock() {
		Stock actualStock = repositoryImpl.getStock(ID);
		assertEquals(stock, actualStock);
	}
	
	@Test
	public void testUpdateStock() throws StocksException {
		repositoryImpl.updateStockPrice(ID, PRICE_300);
		assertEquals(PRICE_300, repositoryImpl.getStock(ID).getCurrentPrice(),0);
	}
	
	@Test
	public void testAddStock() throws StocksException {
		repositoryImpl.addStock(ID2, STOCK_NAME2, 100.50);
		assertNotNull(repositoryImpl.getStock(ID2));
	}
}
