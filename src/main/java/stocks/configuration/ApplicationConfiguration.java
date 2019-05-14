package stocks.configuration;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("myapp")
public class ApplicationConfiguration {

    private Map<Integer, String> stocks;

    public Map<Integer, String> getStocks() {
        return stocks;
    }

	public void setStocks(Map<Integer, String> stocks) {
		this.stocks = stocks;
	}
    
}