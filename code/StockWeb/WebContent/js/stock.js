/**
 * 
 */
QueryString = {  
    data: {},  
    Initial: function() {  
        var aPairs, aTmp;  
        var queryString = new String(window.location.search);  
        queryString = queryString.substr(1, queryString.length); //remove   "?"     
        aPairs = queryString.split("&");  
        for (var i = 0; i < aPairs.length; i++) {  
            aTmp = aPairs[i].split("=");  
            this.data[aTmp[0]] = aTmp[1];  
        }  
    },  
    GetValue: function(key) {  
        return this.data[key];  
    }  
}  

RuleInfo = {
	selectedRuleId: "",
	selectedRuleName: "",
	selectedRuleHisId: "",
	setRuleId: function(ruleId) {
		this.selectedRuleId = ruleId;
	},
	getRuleId: function() {
		return this.selectedRuleId;
	},
	setRuleName: function(ruleName) {
		this.selectedRuleName = ruleName;
	},
	getRuleName: function() {
		return this.selectedRuleName;
	},
	setRuleHisId: function(hisId) {
		this.selectedRuleHisId = hisId;
	},
	getRuleHisId: function() {
		return this.selectedRuleHisId;
	}
}