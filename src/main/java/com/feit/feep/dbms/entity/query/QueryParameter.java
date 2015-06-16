package com.feit.feep.dbms.entity.query;

public class QueryParameter {

    private String fieldName;
    private String parameterValue;

    public QueryParameter(String fieldName, String parameterValue) {
        super();
        this.fieldName = fieldName;
        this.parameterValue = parameterValue;
        this.condition = Condition.LIKE;
    }

    public QueryParameter(String fieldName, String parameterValue, Condition condition) {
        this(fieldName, parameterValue);
        this.condition = condition;
    }

    private Condition condition;

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
