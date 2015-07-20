package com.feit.feeptest.dbms.service;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.datasource.Relation;
import com.feit.feep.dbms.entity.datasource.TableRelationType;
import com.feit.feep.dbms.entity.module.FeepModule;
import com.feit.feep.dbms.entity.module.FeepModuleField;
import com.feit.feep.dbms.entity.module.FeepTableFieldRelation;
import com.feit.feep.dbms.entity.module.FeepTableModuleRelation;
import com.feit.feep.dbms.entity.query.Condition;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.dbms.service.IModuleManagementService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据模型管理service test
 * Created by ZhangGang on 2015/7/20 0020.
 */
public class TestModuleManagementService extends FeepJUnit {

    @Autowired
    private IModuleManagementService moduleManagementService;

    @Test
    public void test() throws Exception {
        FeepModule feepModule = new FeepModule();
        feepModule.setName("feepModuleTestService");
        feepModule.setShowname("s啦啦啦s");
        feepModule.setDescription("测试模型");
        List<FeepModuleField> moduleFields = new LinkedList<FeepModuleField>();
        for (int i = 0; i < 15; i++) {
            FeepModuleField mm = new FeepModuleField();
            mm.setName("moduleservicefield" + i);
            mm.setShowname("模型测试" + i);
            mm.setCode("aaa" + i);
            mm.setSearchable(1 + i);
            mm.setSort(1 + i);
            mm.setTablefieldid("ttt" + i);
            moduleFields.add(mm);
        }
        List<FeepTableModuleRelation> tableModuleRelations = new LinkedList<FeepTableModuleRelation>();
        for (int i = 0; i < 5; i++) {
            FeepTableModuleRelation newTMR = new FeepTableModuleRelation();
            newTMR.setTableid("aaa");
            newTMR.setTableType(TableRelationType.mainTable.getType());
            newTMR.setRelationType(Relation.LEFT_JOIN.getRelationType());
            List<FeepTableFieldRelation> tableFieldRelationList = new LinkedList<FeepTableFieldRelation>();
            for (int j = 0; j < 15; j++) {
                FeepTableFieldRelation newTableField = new FeepTableFieldRelation();
                newTableField.setCondition(Condition.EQUALS.name());
                newTableField.setMainmodulefieldid("aaa111");
                newTableField.setSubtablefieldid("bbb222");
                tableFieldRelationList.add(newTableField);
            }
            newTMR.setTableFieldRelations(tableFieldRelationList);
            tableModuleRelations.add(newTMR);
        }
        String id = moduleManagementService.addModule(feepModule, moduleFields, tableModuleRelations);
        boolean ret = moduleManagementService.modifyModule(feepModule, moduleFields, tableModuleRelations);
        Assert.assertTrue(ret);
        FeepModule findById = moduleManagementService.findFeepModuleById(id);
        Assert.assertEquals(feepModule.getShowname(), findById.getShowname());
        FeepQueryBean queryBean = new FeepQueryBean();
        List<QueryParameter> queryParameters = new LinkedList<QueryParameter>();
        queryParameters.add(new QueryParameter("id", id));
        queryBean.setQueryParameters(queryParameters);
        EntityBeanSet ebs = moduleManagementService.queryFeepModule(queryBean);
        Assert.assertEquals(ebs.get(0).getString("showname"), feepModule.getShowname());
        moduleManagementService.deleteModuleById(id);
    }
}
