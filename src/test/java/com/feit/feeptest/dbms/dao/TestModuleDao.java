package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.dbms.dao.IFeepModuleDao;
import com.feit.feep.dbms.entity.module.FeepModule;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试数据模型dao
 * Created by ZhangGang on 2015/7/18 0018.
 */
public class TestModuleDao extends FeepJUnit {
    @Autowired
    private IFeepModuleDao feepModuleDao;

    @Test
    public void test() throws Exception {
        FeepModule module = new FeepModule();
        module.setName("feepModuleTest111");
        module.setShowname("啦啦啦");
        module.setDescription("测试模型");
        String id = feepModuleDao.addModule(module);
        module.setShowname("测试模型测试模型");
        module.setId(id);
        feepModuleDao.updateModule(module);
        FeepModule findModule = feepModuleDao.findModuleById(id);
        Assert.assertEquals(module.getShowname(), findModule.getShowname());
        List<FeepModule> feepModuleList = feepModuleDao.findAll();
        boolean hasModule = false;
        for (FeepModule feepModule : feepModuleList) {
            if (feepModule.getId().equals(id)) {
                hasModule = true;
                break;
            }
        }
        Assert.assertTrue(hasModule);
        List<FeepModule> feepModuleList2 = feepModuleDao.queryModule(new FeepQueryBean());
        Assert.assertEquals(feepModuleList.size(), feepModuleList2.size());
        boolean isDel = feepModuleDao.deleteModuleById(id);
        Assert.assertTrue(isDel);
    }
}
