package stock.http.impl;

import java.util.List;

import stock.http.HTTPCaller;
import stock.http.HTTPQuery;
import stock.vo.StockInfo;

public class CompositeHTTPQuery implements HTTPQuery {
	
	protected List<HTTPQuery> queryList;

	@Override
	public void richStockInfo(StockInfo stock) throws Exception {
		// TODO Auto-generated method stub
		if (queryList != null && queryList.size() > 0) {
			for(HTTPQuery query : queryList) {
				query.richStockInfo(stock);
			}
		}

	}

	@Override
	public void setHTTPCaller(HTTPCaller caller) {
		// TODO Auto-generated method stub

	}

	@Override
	public HTTPCaller getHTTPCaller() {
		// TODO Auto-generated method stub
		return null;
	}

}
