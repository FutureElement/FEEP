package com.feit.feep.ui.grid;

import com.feit.feep.dbms.entity.EntityBeanSet;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public class ResultList implements Serializable {

    private static final long serialVersionUID = 5212995046987164338L;
    private EntityBeanSet rows;
    private int pageSize;
    private int pageNum;
    private int totalPageSize;
    private List<Column> columns;

    public EntityBeanSet getRows() {
        return rows;
    }

    public void setRows(EntityBeanSet rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
