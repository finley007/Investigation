package stock.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HTTPGetCaller extends HTTPCaller {
	
	protected HTTPGetCaller(Method method, String url) {
		this.method = method;
		this.url = url;
	}
	
	public String callHTTPServ(Map params) throws Exception {
		if (url == null || "".equals(url)) {
			throw new Exception("URL is null");
		}
         URL getUrl = new URL(url); 
         HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
         connection.connect(); 
         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
         StringBuffer sb = new StringBuffer();
         String lines; 
         while ((lines = reader.readLine()) != null) { 
                 sb.append(lines); 
         } 
         reader.close(); 
         connection.disconnect();
         return sb.toString();
	}

}
