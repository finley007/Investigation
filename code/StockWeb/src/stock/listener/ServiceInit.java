package stock.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import stock.timer.Timer;

public class ServiceInit implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Timer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Timer.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
