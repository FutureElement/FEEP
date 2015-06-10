package com.feit.feep.ui.dropdown;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public class SelectItem implements Serializable {

    private static final long serialVersionUID = -2386423242104359167L;

    /*选项id*/
    private String codeId;
    /*选项显示值*/
    private String codeValue;
    /*选项属性值*/
    private Map<String, Object> attr;


    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public Map<String, Object> getAttr() {
        return attr;
    }

    public void setAttr(String key, Object value) {
        if (null == attr) {
            attr = new HashMap<String, Object>();
        }
        attr.put(key, value);
    }
}
