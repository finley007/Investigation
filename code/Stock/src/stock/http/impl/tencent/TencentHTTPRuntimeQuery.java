package stock.http.impl.tencent;

import java.text.MessageFormat;

import stock.analysis.TencentStockRuntimeParser;
import stock.http.HTTPQuery;
import stock.http.impl.BaseHTTPQuery;
import stock.vo.StockInfo;

public class TencentHTTPRuntimeQuery extends BaseHTTPQuery implements HTTPQuery {

	public TencentHTTPRuntimeQuery() {
		this.parser = new TencentStockRuntimeParser();
	}
	
	@Override
	protected String getURL(StockInfo stock) {
		// TODO Auto-generated method stub
		return MessageFormat.format("http://qt.gtimg.cn/q={0}", new Object[]{stock.getCode()});
	}

}
