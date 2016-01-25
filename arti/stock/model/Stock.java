package stock.model;

public class Stock {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_stock.CODE
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_stock.NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_stock.LABEL
     *
     * @mbggenerated
     */
    private String label;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_stock.CODE
     *
     * @return the value of all_stock.CODE
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_stock.CODE
     *
     * @param code the value for all_stock.CODE
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_stock.NAME
     *
     * @return the value of all_stock.NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_stock.NAME
     *
     * @param name the value for all_stock.NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_stock.LABEL
     *
     * @return the value of all_stock.LABEL
     *
     * @mbggenerated
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_stock.LABEL
     *
     * @param label the value for all_stock.LABEL
     *
     * @mbggenerated
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}