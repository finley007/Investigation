package stock.alert.action.impl;

import stock.alert.action.AlertAction;
import stock.tool.EmailSender;

public class EmailAction implements AlertAction {
	
	private static String ALERT_SUBJUCT = "Stock alert:";
	
	private static String TO_EMAIL = "finley007@163.com";

	@Override
	public void doAction(String stockCode, String message) throws Exception {
		// TODO Auto-generated method stub
		EmailSender sender = new EmailSender();
		sender.sendTextMail(ALERT_SUBJUCT + stockCode, message, TO_EMAIL);
	}

	
}
