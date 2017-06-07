package stock.feed.http.tencent;

import java.text.MessageFormat;

import stock.feed.http.HTTPFeeder;
import stock.parse.impl.tencent.TencentStockFlowParser;

/**
 * @author liuli
 *
 * Call Tencent service to get the stock information
 */
public class TencentHTTPFlowFeeder extends HTTPFeeder {
	
	public TencentHTTPFlowFeeder() {
		this.parser = new TencentStockFlowParser();
	}

	protected String getURL(String stockCode) {
		return MessageFormat.format("http://qt.gtimg.cn/q=ff_{0}", new Object[]{stockCode});
	}

}
