package stock.vo;

import stock.cache.StockCache;

/**
 * @author liuli
 *
 *	Stock VO
 */
public class Stock {
	
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
	
	private String code;
	
	private String name;

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
	
}
