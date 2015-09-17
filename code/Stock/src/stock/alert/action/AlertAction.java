package stock.alert.action;

public interface AlertAction {
	
	public void doAction(String stockCode, String message) throws Exception;

}
