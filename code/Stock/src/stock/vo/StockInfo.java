package stock.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuli
 *
 * Stock information VO
 */
public class StockInfo {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	
	public StockInfo(Stock stock) {
		this.stock = stock;
	}
	
	private Stock stock;
	
	private Date time;
	
	private Double mainInflow = 0.0;
	
	private Double mainOutflow = 0.0;
	
	private Double retailInflow = 0.0;
	
	private Double retailOutflow = 0.0;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getMainInflow() {
		return mainInflow;
	}
	
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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
	
	public String toString() {
		StringBuffer sb = new StringBuffer("Stock info: \n");
		sb.append("name: " + this.stock.getName() + "\n");
		sb.append("code: " + this.stock.getCode() + "\n");
		sb.append("main inflow:" + this.mainInflow + "\n");
		sb.append("main outflow:" + this.mainOutflow + "\n");
		sb.append("retail inflow:" + this.retailInflow + "\n");
		sb.append("retail outflow:" + this.retailOutflow + "\n");
		return sb.toString();
	}

}
