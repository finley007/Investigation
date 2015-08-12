package stock.run;

import stock.analysis.StockAnalysis;
import stock.analysis.impl.ShowDiagram;


public class Runner {

	public static void main(String[] args) {
//		BaseExcecuter executer = new TrendAnalysisExecuter();
//		Date start = new Date();
//		executer.run();
//		Date end = new Date();
//		System.out.println("-------------------------------------------------");
//		System.out.println("Using time: " + (end.getTime() - start.getTime()));
		StockAnalysis analysis = new ShowDiagram();
		try {
			int size = 10;
			int init = 7;
			analysis.doAnalysis("20150811100804751RULE_HISTORY_1", init * size + 1, size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
