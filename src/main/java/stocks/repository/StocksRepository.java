package stocks.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import stocks.exception.StocksException;
import stocks.model.Stock;

@Repository
public interface StocksRepository {
    List<Stock> getStockList();
    Stock getStock(int id);
    void updateStockPrice(int id, double price) throws StocksException;
    void addStock(int id, String name, double currentPrice) throws StocksException;

}
