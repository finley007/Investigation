package stock.http.impl;

import java.util.ArrayList;

import stock.http.HTTPQuery;
import stock.http.impl.sina.SinaHTTPHistoryQuery;
import stock.http.impl.tencent.TencentHTTPFlowQuery;
import stock.http.impl.tencent.TencentHTTPRuntimeQuery;

public class MyHTTPQuery extends CompositeHTTPQuery {
	
	public MyHTTPQuery() {
		queryList = new ArrayList<HTTPQuery>();
		queryList.add(new TencentHTTPFlowQuery());
		queryList.add(new TencentHTTPRuntimeQuery());
		queryList.add(new SinaHTTPHistoryQuery());
	}

}
