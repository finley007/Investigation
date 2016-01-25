package stock.feed;

import java.util.List;

import stock.model.MyStock;

public class CompositeFeeder implements InfoFeeder {
	
	protected List<InfoFeeder> queryList;

	@Override
	public void feedInfo(MyStock stock) throws Exception {
		// TODO Auto-generated method stub
		if (queryList != null && queryList.size() > 0) {
			for(InfoFeeder query : queryList) {
				query.feedInfo(stock);
			}
		}
	}

}
