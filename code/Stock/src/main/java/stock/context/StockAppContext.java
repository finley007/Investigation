package stock.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StockAppContext {
	
	private static ApplicationContext ctx;
	
	public static Object getBean(String beanName) {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		}
		return ctx.getBean(beanName);
	}

}
