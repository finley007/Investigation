package stock.vo;

import java.sql.ResultSet;
import java.util.Date;

import stock.util.StockUtils;

public class MyStockInfo extends StockInfo {
	
	private String transId;
	
	protected Double profit = 0.0;
	
	protected Double profitRate = 0.0;
	
	private Integer quantity = 0;
	
	private Date buyingTime;
	
	private Double buyingPrice = 0.0;
	
	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(Double profitRate) {
		this.profitRate = profitRate;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Double price) {
		this.buyingPrice = price;
		caculateProfit();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		caculateProfit();
	}

	public Date getBuyingTime() {
		return buyingTime;
	}

	public void setBuyingTime(Date time) {
		this.buyingTime = time;
	}
	
	//Overwrite the set current price method
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
		caculateProfit();
	}

	private void caculateProfit() {
		try {
			this.profit = StockUtils.round(this.quantity * (this.currentPrice - this.buyingPrice), 2);
			this.profitRate = StockUtils.round((this.profit * 100) / (this.quantity * this.buyingPrice), 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
