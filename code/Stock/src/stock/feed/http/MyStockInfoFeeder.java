package stock.feed.http;

import java.util.ArrayList;

import stock.feed.CompositeFeeder;
import stock.feed.InfoFeeder;
import stock.feed.http.sina.SinaHTTPHistoryFeeder;
import stock.feed.http.tencent.TencentHTTPFlowFeeder;
import stock.feed.http.tencent.TencentHTTPRuntimeFeeder;

public class MyStockInfoFeeder extends CompositeFeeder {
	
	public MyStockInfoFeeder() {
		queryList = new ArrayList<InfoFeeder>();
		queryList.add(new TencentHTTPFlowFeeder());
		queryList.add(new TencentHTTPRuntimeFeeder());
		queryList.add(new SinaHTTPHistoryFeeder());
	}

}
