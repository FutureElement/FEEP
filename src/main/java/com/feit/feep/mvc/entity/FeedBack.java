package com.feit.feep.mvc.entity;

import java.util.HashMap;
import java.util.Map;

public class FeedBack {

    private Map<String, Object> attributes;
    private Object              result;
    private int                 status;

    public FeedBack() {
        attributes = new HashMap<String, Object>();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
