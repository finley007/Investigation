package stock.model;

public class RuleResult {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_result.ID
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_result.HISTORY_ID
     *
     * @mbggenerated
     */
    private String historyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_result.STOCK_CODE
     *
     * @mbggenerated
     */
    private String stockCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_result.FIRST_DAY_TREND
     *
     * @mbggenerated
     */
    private Double firstDayTrend;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_result.SECOND_DAY_TREND
     *
     * @mbggenerated
     */
    private Double secondDayTrend;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_result.THIRD_DAY_TREND
     *
     * @mbggenerated
     */
    private Double thirdDayTrend;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_result.ID
     *
     * @return the value of rule_result.ID
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_result.ID
     *
     * @param id the value for rule_result.ID
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_result.HISTORY_ID
     *
     * @return the value of rule_result.HISTORY_ID
     *
     * @mbggenerated
     */
    public String getHistoryId() {
        return historyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_result.HISTORY_ID
     *
     * @param historyId the value for rule_result.HISTORY_ID
     *
     * @mbggenerated
     */
    public void setHistoryId(String historyId) {
        this.historyId = historyId == null ? null : historyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_result.STOCK_CODE
     *
     * @return the value of rule_result.STOCK_CODE
     *
     * @mbggenerated
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_result.STOCK_CODE
     *
     * @param stockCode the value for rule_result.STOCK_CODE
     *
     * @mbggenerated
     */
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_result.FIRST_DAY_TREND
     *
     * @return the value of rule_result.FIRST_DAY_TREND
     *
     * @mbggenerated
     */
    public Double getFirstDayTrend() {
        return firstDayTrend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_result.FIRST_DAY_TREND
     *
     * @param firstDayTrend the value for rule_result.FIRST_DAY_TREND
     *
     * @mbggenerated
     */
    public void setFirstDayTrend(Double firstDayTrend) {
        this.firstDayTrend = firstDayTrend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_result.SECOND_DAY_TREND
     *
     * @return the value of rule_result.SECOND_DAY_TREND
     *
     * @mbggenerated
     */
    public Double getSecondDayTrend() {
        return secondDayTrend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_result.SECOND_DAY_TREND
     *
     * @param secondDayTrend the value for rule_result.SECOND_DAY_TREND
     *
     * @mbggenerated
     */
    public void setSecondDayTrend(Double secondDayTrend) {
        this.secondDayTrend = secondDayTrend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_result.THIRD_DAY_TREND
     *
     * @return the value of rule_result.THIRD_DAY_TREND
     *
     * @mbggenerated
     */
    public Double getThirdDayTrend() {
        return thirdDayTrend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_result.THIRD_DAY_TREND
     *
     * @param thirdDayTrend the value for rule_result.THIRD_DAY_TREND
     *
     * @mbggenerated
     */
    public void setThirdDayTrend(Double thirdDayTrend) {
        this.thirdDayTrend = thirdDayTrend;
    }
}