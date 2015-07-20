package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepModuleFieldDao;
import com.feit.feep.dbms.entity.module.FeepModuleField;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据模型字段dao测试
 * Created by ZhangGang on 2015/7/18 0018.
 */
public class TestModuleField extends FeepJUnit {

    @Autowired
    private IFeepModuleFieldDao moduleFieldDao;

    @Test
    public void test() throws Exception {
        String moduleId = "123456module";
        FeepModuleField moduleField = new FeepModuleField();
        moduleField.setModuleid(moduleId);
        moduleField.setName("moduleAAa");
        moduleField.setShowname("模型测试");
        moduleField.setCode("aaa");
        moduleField.setSearchable(1);
        moduleField.setSort(1);
        moduleField.setTablefieldid("ttt");
        String id = moduleFieldDao.addModuleField(moduleField);
        moduleField.setId(id);
        moduleField.setShowname("moduleBBb");
        moduleFieldDao.updateModuleField(moduleField);
        FeepModuleField findById = moduleFieldDao.findModuleFieldById(id);
        Assert.assertEquals(moduleField.getShowname(), findById.getShowname());
        moduleFieldDao.deleteModuleFieldById(id);
        List<FeepModuleField> newList = new LinkedList<FeepModuleField>();
        for (int i = 0; i < 15; i++) {
            FeepModuleField mm = new FeepModuleField();
            mm.setModuleid(moduleId);
            mm.setName("moduleAAa" + i);
            mm.setShowname("模型测试" + i);
            mm.setCode("aaa" + i);
            mm.setSearchable(1 + i);
            mm.setSort(1 + i);
            mm.setTablefieldid("ttt" + i);
            newList.add(mm);
        }
        String[] ids = moduleFieldDao.addModuleFields(newList);
        List<FeepModuleField> updatesList = new LinkedList<FeepModuleField>();
        for (int i = 0; i < ids.length; i++) {
            FeepModuleField mm = new FeepModuleField();
            mm.setId(ids[i]);
            mm.setModuleid(moduleId + "update");
            mm.setName("moduleAAa" + i);
            mm.setShowname("模型测试" + i);
            mm.setCode("aaa" + i);
            mm.setSearchable(1 + i);
            mm.setSort(1 + i);
            mm.setTablefieldid("ttt" + i);
            updatesList.add(mm);
        }
        moduleFieldDao.batchUpdateModuleFields(updatesList);
        List<FeepModuleField> findByModuleId = moduleFieldDao.findModuleFieldsByModuleId(moduleId + "update");
        Assert.assertEquals(findByModuleId.size(), 15);
        List<FeepModuleField> findAll = moduleFieldDao.findAll();
        Global.getInstance().logInfo(findAll.size());
        moduleFieldDao.deleteModuleFieldByIds(ids);
        List<FeepModuleField> testDel = new LinkedList<FeepModuleField>();
        for (int i = 0; i < 15; i++) {
            FeepModuleField mm = new FeepModuleField();
            mm.setModuleid(moduleId);
            mm.setName("moduleAAa" + i);
            mm.setShowname("模型测试" + i);
            mm.setCode("aaa" + i);
            mm.setSearchable(1 + i);
            mm.setSort(1 + i);
            mm.setTablefieldid("ttt" + i);
            testDel.add(mm);
        }
        moduleFieldDao.addModuleFields(testDel);
        moduleFieldDao.deleteModuleFieldByModuleId(moduleId);
    }
}
