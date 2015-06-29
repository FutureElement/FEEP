package com.feit.feep.dbms.entity.dictionary;

import java.io.Serializable;

public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String[] column = {"id", "dictionaryname", "showname", "description"};

    private String id;
    private String dictionaryname;
    private String showname;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictionaryname() {
        return dictionaryname;
    }

    public void setDictionaryname(String dictionaryname) {
        this.dictionaryname = dictionaryname;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
