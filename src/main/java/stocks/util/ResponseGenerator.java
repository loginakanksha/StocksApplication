package stocks.util;

import java.util.List;

import org.springframework.stereotype.Component;

import stocks.exception.ErrorCode;
import stocks.model.Stock;
import stocks.model.StocksResponse;

@Component
public class ResponseGenerator {
	
	public StocksResponse createResponse(ErrorCode errorCode, Stock stock, List<Stock> stocks) {
		StocksResponse stockResponse = new StocksResponse();
		stockResponse.setResponseCode(errorCode.getErrorCode());
		stockResponse.setMessage(errorCode.getMessage());
		stockResponse.setStock(stock);
		stockResponse.setStocks(stocks);
		return stockResponse;
	}

}
