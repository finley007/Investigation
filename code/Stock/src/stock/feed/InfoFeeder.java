package stock.feed;

import stock.model.MyStock;

public interface InfoFeeder {
	
	public void feedInfo(MyStock stock) throws Exception;

}
