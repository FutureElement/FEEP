package com.feit.feep.dbms.entity.dictionary;

public class DictionaryItem {
    
    private String id;
    private String key;
    private String value;
    private String sortnum;
    private String description;
    private String dictionaryid;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getSortnum() {
        return sortnum;
    }
    public void setSortnum(String sortnum) {
        this.sortnum = sortnum;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDictionaryid() {
        return dictionaryid;
    }
    public void setDictionaryid(String dictionaryid) {
        this.dictionaryid = dictionaryid;
    }
    
}
