package stock.http;

import java.util.Map;

public class HTTPPostCaller extends HTTPCaller {
	
	protected HTTPPostCaller(Method method) {
		this.method = method;
	}
	
	public String callHTTPServ(Map params) throws Exception {
		return "";
	}

}
