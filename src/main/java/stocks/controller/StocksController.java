package stocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stocks.model.StocksRequest;
import stocks.model.StocksResponse;
import stocks.reqHandler.StocksRequestHandler;

@RestController
@RequestMapping("/api/stocks")
public class StocksController {
	
	@Autowired
	private StocksRequestHandler reqHandler;
	
    @RequestMapping(method=RequestMethod.GET)
    public StocksResponse getStocks() {
    	return reqHandler.handleGetAllStocksRequest();
    }
    
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public StocksResponse getStock(@RequestParam(required=true) int id) {
    	return reqHandler.handleGetStockRequest(id);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public StocksResponse updateStock(@RequestBody StocksRequest req) {
    	return reqHandler.handlePostRequest(req.getId(), req.getCurrentPrice());
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public StocksResponse addStock(@RequestBody StocksRequest req) {
    	return reqHandler.handlePutRequest(req.getId(), req.getName(), req.getCurrentPrice());
    }
}
