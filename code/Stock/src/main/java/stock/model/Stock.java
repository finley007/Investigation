package stock.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import stock.cache.StockCache;
import stock.util.JsonHelper;
import stock.vo.DailyPriceVO;

public class Stock {

    private static String SH_PATTERN = "6\\d{5}$";
    private static String SZ_PATTERN = "[0|3]\\d{5}$";
    private static String SH_PREFIX = "sh";
    private static String SZ_PREFIX = "sz";

    public Stock() {

    }

    public Stock(String code) {
        this.setCode(code);
    }

    private String code;

    private String name;

    private String label;

    protected Double mainInflow = 0.0;

    protected Double mainOutflow = 0.0;

    protected Double retailInflow = 0.0;

    protected Double retailOutflow = 0.0;

    protected Double currentPrice = 0.0;

    protected Map<String, DailyPriceVO> dailyPrice = new HashMap<String, DailyPriceVO>();

    //Price earning ratio
    protected Double per = 0.0;

    String addPrefix(String code) {
        if (Pattern.matches(SH_PATTERN, code)) {
            return SH_PREFIX + code;
        } else if (Pattern.matches(SZ_PATTERN, code)) {
            return SZ_PREFIX + code;
        } else {
            return code;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
        this.code = this.addPrefix(this.code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Double getPer() {
        return per;
    }

    public void setPer(Double per) {
        this.per = per;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getMainInflow() {
        return mainInflow;
    }

    public void setMainInflow(Double mainInflow) {
        this.mainInflow = mainInflow;
    }

    public Double getMainOutflow() {
        return mainOutflow;
    }

    public void setMainOutflow(Double mainOutflow) {
        this.mainOutflow = mainOutflow;
    }

    public Double getRetailInflow() {
        return retailInflow;
    }

    public void setRetailInflow(Double retailInflow) {
        this.retailInflow = retailInflow;
    }

    public Double getRetailOutflow() {
        return retailOutflow;
    }

    public void setRetailOutflow(Double retailOutflow) {
        this.retailOutflow = retailOutflow;
    }

    public void clearDailyPrice() {
        this.dailyPrice = new HashMap<String, DailyPriceVO>();
    }

    public void addDailyPrice(String date, DailyPriceVO vo) {
        this.dailyPrice.put(date, vo);
    }

    public Map<String, DailyPriceVO> getDailyPrice() {
        return dailyPrice;
    }

    public String toJson() {
        return JsonHelper.toJson(this);
    }
}