package stock.alert.action.impl;

import stock.alert.action.AlertAction;
import stock.tool.EmailSender;

public class EmailAction implements AlertAction {

	@Override
	public void doAction(String stockCode, String message) throws Exception {
		// TODO Auto-generated method stub
		EmailSender sender = new EmailSender();
	}

	
}
