package stock.http;

import java.util.Map;

public abstract class HTTPCaller {

	public static enum Method {
		Post, Get
	}
	
	protected Method method;

	protected String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	public static HTTPCaller getIns(Method method, String url) {
		if (method == Method.Get) {
			return new HTTPGetCaller(method, url);
		} else if (method == Method.Post) {
			return new HTTPPostCaller(method, url);
		} else {
			return null;
		}
	}
	
	public abstract String callHTTPServ(Map param) throws Exception;

}
