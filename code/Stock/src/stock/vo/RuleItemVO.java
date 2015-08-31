package stock.vo;

public class RuleItemVO {
	
	private String id;
	
	private String name;
	
	private Integer type;
	
	private String implClz;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getImplClz() {
		return implClz;
	}

	public void setImplClz(String implClz) {
		this.implClz = implClz;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	private String desp;

}
