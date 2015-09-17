package stock.alert.impl;

import java.util.ArrayList;
import java.util.List;

import stock.alert.AlertCondition;

public class CompsiteAlertCondition extends BaseAlertCondition implements
		AlertCondition {
	
	private List<AlertCondition> conditionList = new ArrayList<AlertCondition>();
	
	public void addCondition(AlertCondition condition) {
		this.conditionList.add(condition);
	}
	
	public void clearCondition() {
		this.conditionList.clear();
	}
	
	public Boolean isSatisfy(String stockCode) {
		// TODO Auto-generated method stub
		if (this.conditionList.size() > 0) {
			for (AlertCondition condition : this.conditionList) {
				if (condition.isSatisfy(stockCode)) {
					return true;
				}
			}
		}
		return false;
	} 

}
