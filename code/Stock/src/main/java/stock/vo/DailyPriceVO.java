package stock.vo;

import java.util.Date;

import stock.util.StockConstants;

public class DailyPriceVO {
	
	public DailyPriceVO(String date) {
		this.date = date;
	}
	
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private Double startPrice = 0.0;
	
	private Double endPrice = 0.0;
	
	private Double maxPrice = 0.0;
	
	private Double minPrice = 0.0;
	
	private Long transQuantity = 0l;
	
	private Long transAmout = 0l;

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Long getTransQuantity() {
		return transQuantity;
	}

	public void setTransQuantity(Long transQuantity) {
		this.transQuantity = transQuantity;
	}

	public Long getTransAmout() {
		return transAmout;
	}

	public void setTransAmout(Long transAmout) {
		this.transAmout = transAmout;
	}
	
	public Integer getDaysToToday() throws Exception {
		Date d = StockConstants.sdf_date.parse(this.date);
		return new Date().compareTo(d);
	}

}
