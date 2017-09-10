package stock.rule.impl;

import stock.rule.Rule;
import stock.util.StockConstants;

/**
 * Created by finley on 6/15/17.
 */
public abstract class BaseRule implements Rule {

    protected int windowSize = StockConstants.DEFAULT_TREND_WINDOW_SIZE;

    public void setWindowSize(int size) {
        this.windowSize = size;
    }

}
