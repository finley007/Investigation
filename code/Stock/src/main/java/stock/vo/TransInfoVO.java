package stock.vo;

public class TransInfoVO {
	
	private Integer totalCount = 0;
	
	private Integer gainCount = 0;
	
	private Integer lossCount = 0;
	
	private Double totalProfit = 0.0;
	
	private Double avgProfit = 0.0;
	
	private Double avgProfitRate = 0.0;
	
	private Double maxProfit = 0.0;
	
	private Double maxProfitRate = 0.0;
	
	private Double maxLoss = 0.0;
	
	private Double maxLossRate = 0.0;
	
	private Integer days = 0;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getGainCount() {
		return gainCount;
	}

	public void setGainCount(Integer gainCount) {
		this.gainCount = gainCount;
	}

	public Integer getLossCount() {
		return lossCount;
	}

	public void setLossCount(Integer lossCount) {
		this.lossCount = lossCount;
	}

	public Double getAvgProfit() {
		return avgProfit;
	}

	public void setAvgProfit(Double profit) {
		this.avgProfit = profit;
	}

	public Double getAvgProfitRate() {
		return avgProfitRate;
	}

	public void setAvgProfitRate(Double profitRate) {
		this.avgProfitRate = profitRate;
	}

	public Double getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(Double maxProfit) {
		this.maxProfit = maxProfit;
	}

	public Double getMaxProfitRate() {
		return maxProfitRate;
	}

	public void setMaxProfitRate(Double maxProfitRate) {
		this.maxProfitRate = maxProfitRate;
	}

	public Double getMaxLoss() {
		return maxLoss;
	}

	public void setMaxLoss(Double maxLoss) {
		this.maxLoss = maxLoss;
	}

	public Double getMaxLossRate() {
		return maxLossRate;
	}

	public void setMaxLossRate(Double maxLossRate) {
		this.maxLossRate = maxLossRate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}
}
