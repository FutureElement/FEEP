package com.feit.feep.dbms.controller;

import com.feit.feep.core.Global;
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
        String module = queryBean.getCustomParameter("module");
        Global.getInstance().logInfo(module);
        return tableManagementService.findFeepTableList(queryBean);
    }

}
