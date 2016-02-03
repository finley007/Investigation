package stock.feed;

import java.util.List;

import stock.model.Stock;

public class CompositeFeeder implements InfoFeeder {
	
	protected List<InfoFeeder> queryList;

	@Override
	public void feedInfo(Stock stock) throws Exception {
		// TODO Auto-generated method stub
		if (queryList != null && queryList.size() > 0) {
			for(InfoFeeder query : queryList) {
				query.feedInfo(stock);
			}
		}
	}

}
