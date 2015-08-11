package com.feit.feep.dbms.controller;

import com.feit.feep.core.annotation.FeepController;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.ITableManagementService;
import com.feit.feep.ui.grid.QueryItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据模型管理控制层
 * Created by ZhangGang on 2015/8/1 0001.
 */
@FeepController
public class FeepTableController {

    @Autowired
    private ITableManagementService tableManagementService;

    public EntityBeanSet feep_queryFeepTable(FeepQueryBean queryBean) throws Exception {
        return tableManagementService.findFeepTableList(queryBean);
    }

    public List<QueryItem> feep_getFeepTableSearchFields() throws Exception {
        List<QueryItem> selectItems = new LinkedList<QueryItem>();
        selectItems.add(new QueryItem("name", "表名", FieldType.Text.name(), null));
        selectItems.add(new QueryItem("showname", "显示名", FieldType.Text.name(), null));
        selectItems.add(new QueryItem("system", "所属系统", FieldType.Text.name(), null));
        selectItems.add(new QueryItem("tabletype", "类型", FieldType.Text.name(), null));
        selectItems.add(new QueryItem("description", "描述 ", FieldType.Text.name(), null));
        return selectItems;
    }

}
