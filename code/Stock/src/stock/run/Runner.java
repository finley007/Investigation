package stock.run;

import java.util.Date;

import stock.analysis.StockAnalysis;
import stock.analysis.impl.ShowDiagram;
import stock.analysis.impl.StatisticAnalysis;
import stock.run.impl.MainFlowAnalysisExecuter;


public class Runner {

	public static void main(String[] args) {
		executeRule();
//		checkStocks();
//		doAnalysis();
	}

	private static void doAnalysis() {
		StockAnalysis analysis = new StatisticAnalysis();
		try {
			int size = 10;
			int init = 7;
			analysis.doAnalysis("20150819120824878RULE_HISTORY_1", init * size + 1, size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void checkStocks() {
		StockAnalysis analysis = new ShowDiagram();
		try {
			int size = 0;
			int init = 0;
			analysis.doAnalysis("20150819120824878RULE_HISTORY_1", init * size + 1, size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void executeRule() {
		BaseExcecuter executer = new MainFlowAnalysisExecuter();
		Date start = new Date();
		executer.run();
		Date end = new Date();
		System.out.println("-------------------------------------------------");
		System.out.println("Using time: " + (end.getTime() - start.getTime()));
	}
}
