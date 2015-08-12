package stock.http.impl.sina;

import java.text.MessageFormat;

import stock.http.HTTPQuery;
import stock.http.impl.BaseHTTPQuery;
import stock.parse.impl.sina.SinaStockHistoryParser;
import stock.vo.StockInfo;

public class SinaHTTPHistoryQuery extends BaseHTTPQuery implements HTTPQuery {

	public SinaHTTPHistoryQuery() {
		this.parser = new SinaStockHistoryParser();
	}
	
	@Override
	protected String getURL(StockInfo stock) {
		// TODO Auto-generated method stub
		return MessageFormat.format("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/{0}.phtml", new Object[]{stock.getCode().substring(2)});
	}

}
