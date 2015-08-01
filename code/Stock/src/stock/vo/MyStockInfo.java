package stock.vo;

import java.sql.ResultSet;
import java.util.Date;

public class MyStockInfo extends StockInfo {
	
	public MyStockInfo(Stock stock) {
		super(stock);
	}
	
	private Double buyingPrice;
	
	public Double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Double price) {
		this.buyingPrice = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getBuyingTime() {
		return buyingTime;
	}

	public void setBuyingTime(Date time) {
		this.buyingTime = time;
	}

	private Integer quantity;
	
	private Date buyingTime;
	
	public void initMyStockInfo(ResultSet rs) throws Exception {
		Double price = rs.getDouble("buy_price");
		Integer quantity = rs.getInt("quantity");
		rs.getLong("buy_time");
		Date date = new Date(rs.getLong("buy_time"));
		this.setBuyingPrice(price);
		this.setQuantity(quantity);
		this.setBuyingTime(date);
	}
	

}
