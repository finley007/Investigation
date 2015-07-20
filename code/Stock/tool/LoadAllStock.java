import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;


public class LoadAllStock {
	
	public static void main(String[] args) {
		try {
			String urlTemplate = "http://stock.finance.sina.com.cn/stock/quote/sha{0}.html"; 
			for (int i = 0; i < 1; i++) {
				FileOutputStream fos = new FileOutputStream("e:\\11.txt");
				String url = MessageFormat.format(urlTemplate, new Object[]{i});
				System.out.println(url);
				URL getUrl = new URL(url); 
		        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
		        connection.connect(); 
		        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
		        String lines; 
		        Boolean load = false;
		        while ((lines = reader.readLine()) != null) { 
		              if (load) {
		            	  lines = lines.substring(lines.indexOf(">") + 1, lines.lastIndexOf("<"));
//		            	  System.out.println(new String(lines.getBytes()));
		            	  System.out.println(bytesToHexString(lines.getBytes("GBK")));
		            	  fos.write(lines.getBytes("GBK"));
		            	  load = false;
		            	  break;
		              }
		        	  if (lines.indexOf("<td class=td04 height=23>") > 0) {
//		            	  System.out.println(lines);
//		            	  fos.write(lines.getBytes());
		            	  load = true;
		              }
		        } 
		        reader.close(); 
		        connection.disconnect(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String bytesToHexString(byte[] bytes){
		StringBuilder stringBuilder = new StringBuilder("");  
		if (bytes != null && bytes.length > 0) {
			for(int i = 0;i< bytes.length;i++){
			    int v = bytes[i] & 0xFF;  
		        String hv = Integer.toHexString(v);  
		        if (hv.length() < 2) {  
		            stringBuilder.append(0);  
		        }  
		        stringBuilder.append(hv);
		        stringBuilder.append("|");
			}
		}
		return stringBuilder.toString();
	}  

}
