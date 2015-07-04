package com.feit.feep.dbms.entity.dictionary;

import com.feit.feep.dbms.entity.EntityBean;

import java.io.Serializable;

public class DictionaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String pk = "dictionaryid";

    private String id;
    private String codeid;
    private String codevalue;
    private int sortnum;
    private String description;
    private String dictionaryid;
    private String childrenid;

    public DictionaryItem() {

    }

    public DictionaryItem(String id, String codeid, String codevalue, int sortnum, String description, String dictionaryid, String childrenid) {
        this.id = id;
        this.codeid = codeid;
        this.codevalue = codevalue;
        this.sortnum = sortnum;
        this.description = description;
        this.dictionaryid = dictionaryid;
        this.childrenid = childrenid;
    }

    public DictionaryItem(EntityBean bean) {
        this.id = bean.getString("id");
        this.codeid = bean.getString("codeid");
        this.codevalue = bean.getString("codevalue");
        this.sortnum = bean.getInt("sortnum");
        this.description = bean.getString("description");
        this.dictionaryid = bean.getString("dictionaryid");
        this.childrenid = bean.getString("childrenid");
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryItem item = (DictionaryItem) o;
        if (sortnum != item.sortnum) return false;
        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (codeid != null ? !codeid.equals(item.codeid) : item.codeid != null) return false;
        if (codevalue != null ? !codevalue.equals(item.codevalue) : item.codevalue != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (dictionaryid != null ? !dictionaryid.equals(item.dictionaryid) : item.dictionaryid != null) return false;
        return !(childrenid != null ? !childrenid.equals(item.childrenid) : item.childrenid != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (codeid != null ? codeid.hashCode() : 0);
        result = 31 * result + (codevalue != null ? codevalue.hashCode() : 0);
        result = 31 * result + sortnum;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dictionaryid != null ? dictionaryid.hashCode() : 0);
        result = 31 * result + (childrenid != null ? childrenid.hashCode() : 0);
        return result;
    }
}
