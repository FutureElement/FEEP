package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.dbms.dao.IFeepTableModuleRelationDao;
import com.feit.feep.dbms.entity.datasource.Relation;
import com.feit.feep.dbms.entity.datasource.TableRelationType;
import com.feit.feep.dbms.entity.module.FeepTableModuleRelation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据表模型关系
 * Created by ZhangGang on 2015/7/20 0020.
 */
public class TestIFeepTableModuleRelationDao extends FeepJUnit {
    @Autowired
    private IFeepTableModuleRelationDao tableModuleRelationDao;

    @Test
    public void test() throws Exception {
        FeepTableModuleRelation tableModuleRelation = new FeepTableModuleRelation();
        String fk = "moduleId005";
        tableModuleRelation.setModuleid(fk);
        tableModuleRelation.setTableid("aaa");
        tableModuleRelation.setTableType(TableRelationType.mainTable.getType());
        tableModuleRelation.setRelationType(Relation.LEFT_JOIN.getRelationType());
        String id = tableModuleRelationDao.addTableModuleRelation(tableModuleRelation);
        tableModuleRelation.setTableType(TableRelationType.subTable.getType());
        tableModuleRelation.setId(id);
        tableModuleRelationDao.updateTableModuleRelation(tableModuleRelation);
        tableModuleRelationDao.deleteTableModuleRelationById(id);
        List<FeepTableModuleRelation> newList = new LinkedList<FeepTableModuleRelation>();
        for (int i = 0; i < 15; i++) {
            FeepTableModuleRelation newTMR = new FeepTableModuleRelation();
            newTMR.setModuleid(fk);
            newTMR.setTableid("aaa");
            newTMR.setTableType(TableRelationType.mainTable.getType());
            newTMR.setRelationType(Relation.LEFT_JOIN.getRelationType());
            newList.add(newTMR);
        }
        tableModuleRelationDao.addTableModuleRelations(newList);
        List<FeepTableModuleRelation> findAll = tableModuleRelationDao.findAll();
        findAll.get(0).setTableid("aaa");
        tableModuleRelationDao.batchUpdateTableModuleRelation(findAll);
        tableModuleRelationDao.deleteTableModuleRelationByModuleId(fk);
        List<FeepTableModuleRelation> newList2 = new LinkedList<FeepTableModuleRelation>();
        for (int i = 0; i < 15; i++) {
            FeepTableModuleRelation newTMR = new FeepTableModuleRelation();
            newTMR.setModuleid(fk);
            newTMR.setTableid("aaa");
            newTMR.setTableType(TableRelationType.mainTable.getType());
            newTMR.setRelationType(Relation.LEFT_JOIN.getRelationType());
            newList2.add(newTMR);
        }
        String[] ids2 = tableModuleRelationDao.addTableModuleRelations(newList2);
        tableModuleRelationDao.deleteTableModuleRelationByIds(ids2);
    }

}
