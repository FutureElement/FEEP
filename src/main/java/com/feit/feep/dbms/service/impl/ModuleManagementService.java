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
    public String addModule(final FeepModule module, final List<FeepSubTable> feepSubTables) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                String newId;
                try {
                    //1.addModuleInfo
                    newId = moduleDao.addModule(module);
                    module.setId(newId);
                    String[] newFieldIds = null;
                    String[] newTableModuleRelationIds = null;
                    String[] newTableFieldRelationsIds = null;
                    List<FeepModuleField> newModuleFieldsList = new LinkedList<FeepModuleField>();
                    List<FeepTableModuleRelation> newTableModuleRelations = new LinkedList<FeepTableModuleRelation>();
                    List<FeepTableFieldRelation> newTableFieldRelations = new LinkedList<FeepTableFieldRelation>();
                    if (!FeepUtil.isNull(feepSubTables)) {
                        for (FeepSubTable subTable : feepSubTables) {
                            newModuleFieldsList.addAll(subTable.getSubFields());
                            newTableModuleRelations.add(subTable.getTableModuleRelations());
                            newTableFieldRelations.addAll(subTable.getTableFieldRelations());
                        }
                        //2.add fields
                        newFieldIds = moduleFieldDao.addModuleFields(newModuleFieldsList);
                        //3.add fields
                        newTableModuleRelationIds = tableModuleRelationDao.addTableModuleRelations(newTableModuleRelations);
                        //4.add fields
                        newTableFieldRelationsIds = tableFieldRelationDao.addTableFieldRelations(newTableFieldRelations);
                    }
                    //add cache
                    Global.getInstance().getCacheManager().put(CachePool.MODULECACHE, newId, module);
                    if (!FeepUtil.isNull(newFieldIds)) {
                        for (int i = 0; i < newFieldIds.length; i++) {
                            Global.getInstance().getCacheManager().put(CachePool.MODULEFIELDCACHE, newFieldIds[i], newModuleFieldsList.get(i));
                        }
                    }
                    if (!FeepUtil.isNull(newTableModuleRelationIds)) {
                        for (int i = 0; i < newTableModuleRelationIds.length; i++) {
                            Global.getInstance().getCacheManager().put(CachePool.TABLEMODULERELATIONCACHE, newTableModuleRelationIds[i], newTableModuleRelations.get(i));
                        }
                    }
                    if (!FeepUtil.isNull(newTableFieldRelationsIds)) {
                        for (int i = 0; i < newTableFieldRelationsIds.length; i++) {
                            Global.getInstance().getCacheManager().put(CachePool.TABLEFIELDRELATIONCACHE, newTableFieldRelationsIds[i], newTableFieldRelations.get(i));
                        }
                    }
                } catch (Exception e) {
                    newId = null;
                    transactionStatus.setRollbackOnly();
                    Global.getInstance().logError("addModule error", e);
                }
                return newId;
            }
        });
    }

    @Override
    public boolean deleteModuleById(String id) throws Exception {
        return false;
    }

    @Override
    public boolean modifyModule(FeepModule feepModule, List<FeepModuleField> moduleFields, List<FeepSubTable> subTableList) throws Exception {
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
    public List<FeepSubTable> getFeepSubTableByModuleId(String moduleId) throws Exception {
        return null;
    }
}
