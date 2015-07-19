package stock.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuli
 *
 * Stock information VO
 */
public class StockInfo {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	
	Date time;
	
	public StockInfo(Date now) {
		this.time = now;
	}
	
	private Integer mainInflow = 0;
	
	private Integer mainOutflow = 0;
	
	private Integer retailInflow = 0;
	
	private Integer retailOutflow = 0;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getMainInflow() {
		return mainInflow;
	}

	public void setMainInflow(Integer mainInflow) {
		this.mainInflow = mainInflow;
	}

	public Integer getMainOutflow() {
		return mainOutflow;
	}

	public void setMainOutflow(Integer mainOutflow) {
		this.mainOutflow = mainOutflow;
	}

	public Integer getRetailInflow() {
		return retailInflow;
	}

	public void setRetailInflow(Integer retailInflow) {
		this.retailInflow = retailInflow;
	}

	public Integer getRetailOutflow() {
		return retailOutflow;
	}

	public void setRetailOutflow(Integer retailOutflow) {
		this.retailOutflow = retailOutflow;
	}

}
