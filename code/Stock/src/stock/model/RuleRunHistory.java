package stock.model;

import java.util.Date;

public class RuleRunHistory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_run_history.ID
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_run_history.RULE_ID
     *
     * @mbggenerated
     */
    private String ruleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_run_history.RUN_TIME
     *
     * @mbggenerated
     */
    private Date runTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_run_history.RESULT
     *
     * @mbggenerated
     */
    private Integer result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_run_history.TIME_COST
     *
     * @mbggenerated
     */
    private Integer timeCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rule_run_history.STOCK_NUM
     *
     * @mbggenerated
     */
    private Integer stockNum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_run_history.ID
     *
     * @return the value of rule_run_history.ID
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_run_history.ID
     *
     * @param id the value for rule_run_history.ID
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_run_history.RULE_ID
     *
     * @return the value of rule_run_history.RULE_ID
     *
     * @mbggenerated
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_run_history.RULE_ID
     *
     * @param ruleId the value for rule_run_history.RULE_ID
     *
     * @mbggenerated
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_run_history.RUN_TIME
     *
     * @return the value of rule_run_history.RUN_TIME
     *
     * @mbggenerated
     */
    public Date getRunTime() {
        return runTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_run_history.RUN_TIME
     *
     * @param runTime the value for rule_run_history.RUN_TIME
     *
     * @mbggenerated
     */
    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_run_history.RESULT
     *
     * @return the value of rule_run_history.RESULT
     *
     * @mbggenerated
     */
    public Integer getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_run_history.RESULT
     *
     * @param result the value for rule_run_history.RESULT
     *
     * @mbggenerated
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_run_history.TIME_COST
     *
     * @return the value of rule_run_history.TIME_COST
     *
     * @mbggenerated
     */
    public Integer getTimeCost() {
        return timeCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_run_history.TIME_COST
     *
     * @param timeCost the value for rule_run_history.TIME_COST
     *
     * @mbggenerated
     */
    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rule_run_history.STOCK_NUM
     *
     * @return the value of rule_run_history.STOCK_NUM
     *
     * @mbggenerated
     */
    public Integer getStockNum() {
        return stockNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rule_run_history.STOCK_NUM
     *
     * @param stockNum the value for rule_run_history.STOCK_NUM
     *
     * @mbggenerated
     */
    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }
}