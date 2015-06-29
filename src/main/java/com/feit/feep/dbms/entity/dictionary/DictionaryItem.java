package com.feit.feep.dbms.entity.dictionary;

import java.io.Serializable;

public class DictionaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String codeid;
    private String codevalue;
    private int sortnum;
    private String description;
    private String dictionaryid;
    private String childrenid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeid() {
        return codeid;
    }

    public void setCodeid(String codeid) {
        this.codeid = codeid;
    }

    public String getCodevalue() {
        return codevalue;
    }

    public void setCodevalue(String codevalue) {
        this.codevalue = codevalue;
    }

    public int getSortnum() {
        return sortnum;
    }

    public void setSortnum(int sortnum) {
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

    public String getChildrenid() {
        return childrenid;
    }

    public void setChildrenid(String childrenid) {
        this.childrenid = childrenid;
    }
}
