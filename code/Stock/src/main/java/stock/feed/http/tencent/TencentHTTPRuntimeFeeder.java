package stock.feed.http.tencent;

import java.text.MessageFormat;

import stock.feed.http.HTTPFeeder;
import stock.model.Stock;
import stock.parse.impl.tencent.TencentStockRuntimeParser;

public class TencentHTTPRuntimeFeeder extends HTTPFeeder {

	public TencentHTTPRuntimeFeeder() {
		this.parser = new TencentStockRuntimeParser();
	}
	
	@Override
	protected String getURL(String stockCode) {
		// TODO Auto-generated method stub
		return MessageFormat.format("http://qt.gtimg.cn/q={0}", new Object[]{stockCode});
	}

}
