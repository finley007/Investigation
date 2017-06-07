package stock.feed;

import stock.model.Stock;

public interface InfoFeeder {
	
	public void feedInfo(Stock stock) throws Exception;

}
