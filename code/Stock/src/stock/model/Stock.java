package stock.model;

import java.util.HashMap;
import java.util.Map;

import stock.cache.StockCache;
import stock.util.JsonHelper;
import stock.vo.DailyPriceVO;
import stock.vo.IStockVO;

public class Stock implements IStockVO {
	
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
    
    private String label;
    
    protected Double mainInflow = 0.0;
	
	protected Double mainOutflow = 0.0;
	
	protected Double retailInflow = 0.0;
	
	protected Double retailOutflow = 0.0;
	
	protected Double currentPrice = 0.0;
	
	protected Map<String, DailyPriceVO> dailyPrice = new HashMap<String, DailyPriceVO>();
	
	//Price earning ratio
	protected Double per = 0.0;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
//        this.label = label == null ? null : label.trim();
    	this.label = "";
    }
    
	public Double getPer() {
		return per;
	}

	public void setPer(Double per) {
		this.per = per;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Double getMainInflow() {
		return mainInflow;
	}
	
	public void setMainInflow(Double mainInflow) {
		this.mainInflow = mainInflow;
	}

	public Double getMainOutflow() {
		return mainOutflow;
	}

	public void setMainOutflow(Double mainOutflow) {
		this.mainOutflow = mainOutflow;
	}

	public Double getRetailInflow() {
		return retailInflow;
	}

	public void setRetailInflow(Double retailInflow) {
		this.retailInflow = retailInflow;
	}

	public Double getRetailOutflow() {
		return retailOutflow;
	}

	public void setRetailOutflow(Double retailOutflow) {
		this.retailOutflow = retailOutflow;
	}
	
	public void clearDailyPrice() {
		this.dailyPrice = new HashMap<String, DailyPriceVO>(); 
	}
	
	public void addDailyPrice(String date, DailyPriceVO vo) {
		this.dailyPrice.put(date, vo);
	}
	
	public Map<String, DailyPriceVO> getDailyPrice() {
		return dailyPrice;
	}

	public String toJson() {
		return JsonHelper.toJson(this);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("Stock info: \n");
		sb.append("name: " + this.getName() + "\n");
		sb.append("code: " + this.getCode() + "\n");
		sb.append("main inflow:" + this.mainInflow + "\n");
		sb.append("main outflow:" + this.mainOutflow + "\n");
		sb.append("retail inflow:" + this.retailInflow + "\n");
		sb.append("retail outflow:" + this.retailOutflow + "\n");
		return sb.toString();
	}
}