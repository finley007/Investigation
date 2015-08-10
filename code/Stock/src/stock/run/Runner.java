package stock.run;

import java.util.Date;

import stock.run.impl.TrendAnalysisExecuter;


public class Runner {

	public static void main(String[] args) {
		BaseExcecuter executer = new TrendAnalysisExecuter();
		Date start = new Date();
		executer.run();
		Date end = new Date();
		System.out.println("-------------------------------------------------");
		System.out.println("Using time: " + (end.getTime() - start.getTime()));
	}
}
