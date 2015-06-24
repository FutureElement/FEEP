package com.feit.feep.dbms.init;

import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.ITableManagementService;
import com.feit.feep.util.FeepUtil;
import org.springframework.context.ApplicationContext;

public class InitCacheData {

    private ApplicationContext ctx;

    private ITableManagementService tableManagementService;

    public InitCacheData(ApplicationContext ctx) {
        this.ctx = ctx;
        tableManagementService = ctx.getBean(ITableManagementService.class);
    }

    public void init() throws Exception {

        EntityBeanSet ebs = tableManagementService.findFeepTableList(new FeepQueryBean());
        if (FeepUtil.isNull(ebs)) {
            //加载table到缓存
            //TODO
            //加载tablefield到缓存
        }

    }
}
