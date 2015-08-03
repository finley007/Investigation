package stock.http.impl.tencent;

import java.util.ArrayList;

import stock.http.HTTPQuery;
import stock.http.impl.CompositeHTTPQuery;

public class TencentHTTPQuery extends CompositeHTTPQuery {
	
	public TencentHTTPQuery() {
		queryList = new ArrayList<HTTPQuery>();
		queryList.add(new TencentHTTPFlowQuery());
		queryList.add(new TencentHTTPRuntimeQuery());
	}

}
