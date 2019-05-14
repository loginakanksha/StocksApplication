package stocks.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import stocks.configuration.ApplicationConfiguration;
import stocks.exception.ErrorCode;
import stocks.exception.StocksException;
import stocks.model.Stock;

@Repository
public class StocksRepositoryImpl implements StocksRepository {

	private Map<Integer, Stock> stockMap = new HashMap<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	@Autowired
	ApplicationConfiguration configuration;

	@PostConstruct
	public void init() {

		for (Map.Entry<Integer, String> entry : configuration.getStocks().entrySet()) {
			int id = entry.getKey();
			String[] tokens = entry.getValue().split(",");
			Stock stock = new Stock(Integer.parseInt(tokens[0]), tokens[1].trim(), Double.parseDouble(tokens[2]),
					LocalDateTime.parse(tokens[3].trim(), formatter));
			stockMap.put(id, stock);
		}
	}

	@Override
	public List<Stock> getStockList() {
		List<Stock> stocks = new ArrayList<>();
		for (Map.Entry<Integer, Stock> e : stockMap.entrySet()) {
			stocks.add(e.getValue());
		}
		return stocks;
	}

	@Override
	public Stock getStock(int id) {
		return stockMap.get(id);
	}

	@Override
	public void updateStockPrice(int id, double price) throws StocksException {
		if(!stockMap.containsKey(id)){
			throw new StocksException(ErrorCode.SA_INVALID_STOCKID);
		}
		stockMap.get(id).setCurrentPrice(price);
		stockMap.get(id).setLastUpdate(LocalDateTime.now());
	}

	@Override
	public void addStock(int id, String name, double currentPrice)  throws StocksException{
		if(stockMap.containsKey(id)){
			throw new StocksException(ErrorCode.SA_STOCKID_DUPLICATE);
		}
		Stock stock = new Stock(id, name, currentPrice, LocalDateTime.now());
		stockMap.put(id, stock);
	}

}
