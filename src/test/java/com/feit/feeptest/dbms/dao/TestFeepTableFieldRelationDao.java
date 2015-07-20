package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.dbms.dao.IFeepTableFieldRelationDao;
import com.feit.feep.dbms.entity.module.FeepTableFieldRelation;
import com.feit.feep.dbms.entity.query.Condition;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据表字段关系dao 测试
 * Created by ZhangGang on 2015/7/20 0020.
 */
public class TestFeepTableFieldRelationDao extends FeepJUnit {

    @Autowired
    private IFeepTableFieldRelationDao tableFieldRelationDao;

    @Test
    public void test() throws Exception {
        String tablemodulerelationid = "001Tablemodulerelationid";
        FeepTableFieldRelation feepTableFieldRelation = new FeepTableFieldRelation();
        feepTableFieldRelation.setCondition(Condition.EQUALS.getCndSQL());
        feepTableFieldRelation.setTablemodulerelationid(tablemodulerelationid);
        feepTableFieldRelation.setMainmodulefieldid("aaa111");
        feepTableFieldRelation.setSubtablefieldid("bbb222");
        String id = tableFieldRelationDao.addTableFieldRelation(feepTableFieldRelation);
        feepTableFieldRelation.setId(id);
        feepTableFieldRelation.setMainmodulefieldid("xxx");
        tableFieldRelationDao.updateTableFieldRelation(feepTableFieldRelation);
        tableFieldRelationDao.deleteTableFieldRelationById(id);
        tableFieldRelationDao.deleteTableFieldRelationByRelationId(tablemodulerelationid);
        List<FeepTableFieldRelation> tableFieldRelationList = new LinkedList<FeepTableFieldRelation>();
        for (int i = 0; i < 15; i++) {
            FeepTableFieldRelation newTableField = new FeepTableFieldRelation();
            newTableField.setCondition(Condition.EQUALS.getCndSQL());
            newTableField.setTablemodulerelationid(tablemodulerelationid);
            newTableField.setMainmodulefieldid("aaa111");
            newTableField.setSubtablefieldid("bbb222");
            tableFieldRelationList.add(newTableField);
        }
        String[] ids = tableFieldRelationDao.addTableFieldRelations(tableFieldRelationList);
        List<FeepTableFieldRelation> updateTableFieldRelationList = new LinkedList<FeepTableFieldRelation>();
        for (String id1 : ids) {
            FeepTableFieldRelation newTableField = new FeepTableFieldRelation();
            newTableField.setId(id1);
            newTableField.setCondition(Condition.EQUALS.name());
            newTableField.setTablemodulerelationid(tablemodulerelationid + "test");
            newTableField.setMainmodulefieldid("aaa111");
            newTableField.setSubtablefieldid("bbb222");
            updateTableFieldRelationList.add(newTableField);
        }
        tableFieldRelationDao.batchUpdateTableFieldRelation(updateTableFieldRelationList);
        List<FeepTableFieldRelation> findAll = tableFieldRelationDao.findAll();
        Assert.assertTrue(findAll.size() >= 15);
        tableFieldRelationDao.deleteTableFieldRelationByIds(ids);
        tableFieldRelationDao.deleteTableFieldRelationByRelationIds(new String[]{tablemodulerelationid + "test"});
    }
}
