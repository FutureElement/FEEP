package com.feit.feep.dbms.service.impl;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepModuleDao;
import com.feit.feep.dbms.dao.IFeepModuleFieldDao;
import com.feit.feep.dbms.dao.IFeepTableFieldRelationDao;
import com.feit.feep.dbms.dao.IFeepTableModuleRelationDao;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.*;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.IModuleManagementService;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据模型管理service实现类
 * Created by ZhangGang on 2015/7/6 0006.
 */
@Service
public class ModuleManagementService implements IModuleManagementService {

    @Autowired
    private IFeepModuleDao moduleDao;
    @Autowired
    private IFeepModuleFieldDao moduleFieldDao;
    @Autowired
    private IFeepTableModuleRelationDao tableModuleRelationDao;
    @Autowired
    private IFeepTableFieldRelationDao tableFieldRelationDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String addModule(final FeepModule module, final List<FeepModuleField> moduleFields, final List<FeepTableModuleRelation> tableModuleRelations) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                String moduleId;
                try {
                    //1.addModuleInfo
                    moduleId = moduleDao.addModule(module);
                    module.setId(moduleId);
                    //2.addModuleFields
                    if (!FeepUtil.isNull(moduleFields)) {
                        for (FeepModuleField feepModuleField : moduleFields) {
                            feepModuleField.setModuleid(moduleId);
                        }
                        moduleFieldDao.addModuleFields(moduleFields);
                    }
                    //3.add subTable
                    if (!FeepUtil.isNull(tableModuleRelations)) {
                        List<FeepTableFieldRelation> newTableFieldRelations = new LinkedList<FeepTableFieldRelation>();
                        for (FeepTableModuleRelation tableModuleRelation : tableModuleRelations) {
                            String tableModuleRelationId = tableModuleRelation.getId();
                            if (FeepUtil.isNull(tableModuleRelationId)) {
                                tableModuleRelationId = FeepUtil.getUUID();
                                tableModuleRelation.setId(tableModuleRelationId);
                            }
                            List<FeepTableFieldRelation> tableFieldRelations = tableModuleRelation.getTableFieldRelations();
                            if (!FeepUtil.isNull(tableFieldRelations)) {
                                for (FeepTableFieldRelation tableFieldRelation : tableFieldRelations) {
                                    tableFieldRelation.setTablemodulerelationid(tableModuleRelationId);
                                    newTableFieldRelations.add(tableFieldRelation);
                                }
                            }
                        }
                        tableModuleRelationDao.addTableModuleRelations(tableModuleRelations);
                        if (!FeepUtil.isNull(newTableFieldRelations)) {
                            tableFieldRelationDao.addTableFieldRelations(newTableFieldRelations);
                            //add cache
                            for (FeepTableFieldRelation tableFieldRelation : newTableFieldRelations) {
                                Global.getInstance().getCacheManager().put(CachePool.TABLEFIELDRELATIONCACHE, tableFieldRelation.getId(), tableFieldRelation);
                            }
                        }
                        for (FeepTableModuleRelation tableModuleRelation : tableModuleRelations) {
                            Global.getInstance().getCacheManager().put(CachePool.TABLEMODULERELATIONCACHE, tableModuleRelation.getId(), tableModuleRelation);
                        }
                    }
                    Global.getInstance().getCacheManager().put(CachePool.MODULECACHE, moduleId, module);
                    if (!FeepUtil.isNull(moduleFields)) {
                        for (FeepModuleField feepModuleField : moduleFields) {
                            Global.getInstance().getCacheManager().put(CachePool.MODULEFIELDCACHE, feepModuleField.getId(), feepModuleField);
                        }
                    }
                } catch (Exception e) {
                    moduleId = null;
                    transactionStatus.setRollbackOnly();
                    Global.getInstance().logError("addModule error", e);
                }
                return moduleId;
            }
        });
    }

    @Override
    public boolean deleteModuleById(final String id) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    moduleDao.deleteModuleById(id);
                    EntityBeanSet moduleFields = getModuleFieldsByModuleId(id);
                    EntityBeanSet tableModuleRelations = getFeepTableModuleRelationByModuleId(id);
                    String[] relationIds = null;
                    if (!FeepUtil.isNull(moduleFields)) {
                        moduleFieldDao.deleteModuleFieldByModuleId(id);
                    }
                    if (!FeepUtil.isNull(tableModuleRelations)) {
                        relationIds = tableModuleRelations.getIds();
                        tableFieldRelationDao.deleteTableFieldRelationByRelationIds(relationIds);
                    }
                    tableModuleRelationDao.deleteTableModuleRelationByModuleId(id);
                    //delete cache
                    Global.getInstance().getCacheManager().remove(CachePool.MODULECACHE, id);
                    if (!FeepUtil.isNull(moduleFields)) {
                        Global.getInstance().getCacheManager().removeAll(CachePool.MODULEFIELDCACHE, moduleFields.getIds());
                    }
                    if (!FeepUtil.isNull(relationIds)) {
                        Global.getInstance().getCacheManager().removeAll(CachePool.TABLEMODULERELATIONCACHE, relationIds);
                        for (String relationId : relationIds) {
                            EntityBeanSet tableFieldRelations = getFeepTableFieldRelationByRelationId(relationId);
                            Global.getInstance().getCacheManager().removeAll(CachePool.TABLEFIELDRELATIONCACHE, tableFieldRelations.getIds());
                        }
                    }
                    return true;
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    Global.getInstance().logError("deleteModuleById error", e);
                    return false;
                }
            }
        });
    }

    @Override
    public boolean modifyModule(FeepModule feepModule, List<FeepModuleField> moduleFields, List<FeepTableModuleRelation> subTableList) throws Exception {
        return false;
    }

    @Override
    public FeepModule findFeepModuleById(String id) throws Exception {
        return null;
    }

    @Override
    public EntityBeanSet queryFeepModule(FeepQueryBean queryBean) throws Exception {
        return null;
    }

    @Override
    public EntityBeanSet getModuleFieldsByModuleId(String moduleId) throws Exception {
        return null;
    }

    @Override
    public EntityBeanSet getFeepTableModuleRelationByModuleId(String moduleId) throws Exception {
        return null;
    }

    @Override
    public EntityBeanSet getFeepTableFieldRelationByRelationId(String relationId) throws Exception {
        return null;
    }
}
