package stocks.reqHandler;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks.exception.ErrorCode;
import stocks.exception.StocksException;
import stocks.model.Stock;
import stocks.model.StocksResponse;
import stocks.repository.StocksRepositoryImpl;
import stocks.util.ResponseGenerator;

@Component
public class StocksRequestHandler {
	
	@Autowired
	private StocksRepositoryImpl repositoryImpl;
	
	@Autowired
	private ResponseGenerator responseGen;
	
	public StocksResponse handleGetAllStocksRequest(){
        List<Stock> stocks = repositoryImpl.getStockList();
        return responseGen.createResponse(ErrorCode.SA_SUCCESS, null, stocks);
	}
	
	public StocksResponse handleGetStockRequest(int id){
    	Stock stock = repositoryImpl.getStock(id);
    	if(stock != null) {
    		return responseGen.createResponse(ErrorCode.SA_SUCCESS, stock, null);
    	} else{
    		return responseGen.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
    	}
    }

	public StocksResponse handlePostRequest(String id, String currentPrice){
		if(StringUtils.isBlank(id)) {
			return responseGen.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null);
		}
		if(StringUtils.isBlank(currentPrice)){
			return responseGen.createResponse(ErrorCode.SA_BLANK_PRICE, null, null); 
		}
		if(!StringUtils.isNumeric(id)){
			return responseGen.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		}
		try{
			Double.parseDouble(currentPrice);
		} catch(NumberFormatException e){
			return responseGen.createResponse(ErrorCode.SA_INVALID_PRICE, null, null);
		}
		
    	try{
    		repositoryImpl.updateStockPrice(Integer.parseInt(id), Double.parseDouble(currentPrice));
    	} catch(StocksException e){
    		return responseGen.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
    	}
    	return responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null);
    }

	public StocksResponse handlePutRequest(String id, String name, String currentPrice){
		if(StringUtils.isBlank(id)) {
			return responseGen.createResponse(ErrorCode.SA_BLANK_STOCKID, null, null);
		}
		if(StringUtils.isBlank(name)){
			return responseGen.createResponse(ErrorCode.SA_BLANK_NAME, null, null);
		}
		if(StringUtils.isBlank(currentPrice)){
			return responseGen.createResponse(ErrorCode.SA_BLANK_PRICE, null, null); 
		}
		if(!StringUtils.isNumeric(id)){
			return responseGen.createResponse(ErrorCode.SA_INVALID_STOCKID, null, null);
		}
		try{
			Double.parseDouble(currentPrice);
		} catch(NumberFormatException e){
			return responseGen.createResponse(ErrorCode.SA_INVALID_PRICE, null, null);
		}
		
    	try{
    		repositoryImpl.addStock(Integer.parseInt(id), name, Double.parseDouble(currentPrice));
    	} catch(StocksException e){
    		return responseGen.createResponse(ErrorCode.SA_STOCKID_DUPLICATE, null, null);
    	}
    	return responseGen.createResponse(ErrorCode.SA_SUCCESS, null, null);
    }
}
