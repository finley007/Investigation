package stock.http.impl.tencent;

import java.text.MessageFormat;

import stock.http.HTTPQuery;
import stock.http.impl.BaseHTTPQuery;
import stock.parse.impl.tencent.TencentStockFlowParser;
import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * Call tencent service to get the stock information
 */
public class TencentHTTPFlowQuery extends BaseHTTPQuery implements HTTPQuery {
	
	public TencentHTTPFlowQuery() {
		this.parser = new TencentStockFlowParser();
	}

	protected String getURL(StockInfo stock) {
		return MessageFormat.format("http://qt.gtimg.cn/q=ff_{0}", new Object[]{stock.getCode()});
	}

}
