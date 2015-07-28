package stock.vo;

/**
 * @author liuli
 *
 *	Stock VO
 */
public class Stock {
	
	public Stock() {
		
	}
	
	public Stock(String code, String name) {
		this.code = code;
		this.name = name;
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
