package com.feit.feep.dbms.entity.query;

public enum Condition {
    LIKE("like"),
    NOTLIKE("not like"),
    EQUALS("="),
    NOTEQUALS("!="),
    LT("<"),
    LTE("<="),
    GT(">"),
    GTE(">=");
    
    private String cndSQL;
    
    Condition(String cndSQL){
        this.cndSQL = cndSQL;
    }
    public String getCndSQL(){
        return cndSQL;
    }
}
