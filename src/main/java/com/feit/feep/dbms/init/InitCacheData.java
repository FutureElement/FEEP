package com.feit.feep.dbms.init;

import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.ITableManagementService;
import com.feit.feep.util.FeepUtil;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InitCacheData {

    private ApplicationContext ctx;

    private IFeepTableDao feepTableDao;

    public InitCacheData(ApplicationContext ctx) {
        this.ctx = ctx;
        feepTableDao = ctx.getBean(IFeepTableDao.class);
    }

    public void init() throws Exception {
        List<FeepTable> list = feepTableDao.queryFeepTable(new FeepQueryBean());
        if (FeepUtil.isNull(list)) {
            //加载table到缓存
            //TODO
            //加载tablefield到缓存
        }

    }
}
