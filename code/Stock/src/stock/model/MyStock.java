package stock.model;

import java.util.Date;

public class MyStock {
	
	private Stock stock;
	
    private String transactionId;

    private Double buyPrice;

    private Integer quantity;

    private Date openTime;

    private Integer status;

    private Integer isMonitor;

    private Double profit;

    private Double profitRate;

    private Date closeTime;

    public Stock getStock() {
		return stock;
	}
    
    public void setStock(Stock stock) {
    	this.stock = stock;
    }
    
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsMonitor() {
        return isMonitor;
    }

    public void setIsMonitor(Integer isMonitor) {
        this.isMonitor = isMonitor;
    }

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

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}