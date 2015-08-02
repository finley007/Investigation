package stock.vo;

import stock.cache.StockCache;
import stock.json.JsonHelper;

/**
 * @author liuli
 *
 *	Stock VO
 */
public class Stock implements IStockVO{
	
	public Stock() {
		
	}
	
	public Stock(String code) {
		this.code = code;
		try {
			this.name = StockCache.getNameByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String code = "";
	
	protected String name = "";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toJson() {
		return JsonHelper.toJson(this);
	}
	
}
