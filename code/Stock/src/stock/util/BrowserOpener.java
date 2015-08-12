package stock.util;

import java.awt.Desktop;
import java.net.URI;

public class BrowserOpener {
	
	public static void open(String url) throws Exception {
		URI uri = new URI(url);
        Desktop.getDesktop().browse(uri);
	}
	
	public static void main(String[] args) {
		try {
			open("www.baidu.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
