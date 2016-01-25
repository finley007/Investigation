package stock.feed.http.sina;

import java.text.MessageFormat;

import stock.feed.http.HTTPFeeder;
import stock.parse.impl.sina.SinaStockHistoryParser;

public class SinaHTTPHistoryFeeder extends HTTPFeeder {

	public SinaHTTPHistoryFeeder() {
		this.parser = new SinaStockHistoryParser();
	}
	
	@Override
	protected String getURL(String stockCode) {
		// TODO Auto-generated method stub
		return MessageFormat.format("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/{0}.phtml", new Object[]{stockCode.substring(2)});
	}

}
