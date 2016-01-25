package stock.http;

import java.util.Map;

public class HTTPPostCaller extends HTTPCaller {
	
	protected HTTPPostCaller(Method method, String url) {
		this.method = method;
		this.url = url;
	}
	
	public String callHTTPServ(Map params) throws Exception {
		return "";
	}

}
