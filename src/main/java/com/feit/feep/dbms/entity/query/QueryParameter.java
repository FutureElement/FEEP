package com.feit.feep.dbms.entity.query;

public class QueryParameter {

    private String fieldName;
    private String parameterValue;
    private Condition condition;

    public QueryParameter() {

    }

    public QueryParameter(String fieldName, String parameterValue, Condition condition) {
        this();
        this.fieldName = fieldName;
        this.parameterValue = parameterValue;
        this.condition = condition;
    }

    public QueryParameter(String fieldName, String parameterValue) {
        this(fieldName, parameterValue, Condition.LIKE);
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

}
