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

TransInfo = {
	selectedStock: "*",
	transId: "",
	setStock: function(stockCode) {
		this.selectedStock = stockCode;
	},
	getStock: function() {
		return this.selectedStock;
	},
	setTransId: function(transId) {
		this.transId = transId;
	},
	getTransId: function() {
		return this.transId;
	}
}

function getStockDiagramUrl(stockCode) {
	var stockCode = TransInfo.getStock().toString();
	if (stockCode == '*') {
		return 'http://q.stock.sohu.com/qp/index.html?zs_000001';
	} else {
		return 'http://q.stock.sohu.com/qp/index.html?cn_' + stockCode.substring(2);
	}
}