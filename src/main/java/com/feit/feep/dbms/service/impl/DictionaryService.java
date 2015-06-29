package com.feit.feep.dbms.service.impl;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepDictionaryDao;
import com.feit.feep.dbms.dao.IFeepDictionaryItemDao;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.dbms.service.IDictionaryService;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangGang on 2015/6/29 0029.
 */
@Service
public class DictionaryService implements IDictionaryService {

    @Autowired
    private IFeepDictionaryDao dictionaryDao;

    @Autowired
    private IFeepDictionaryItemDao dictionaryItemDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String addDictionary(Dictionary dictionaryInfo, List<DictionaryItem> dictionaryItems) throws Exception {
        final Dictionary dictionary = dictionaryInfo;
        final List<DictionaryItem> itemList = dictionaryItems;
        return transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                String dictionaryId = null;
                try {
                    dictionaryId = dictionaryDao.addDictionary(dictionary);
                    if (!FeepUtil.isNull(dictionaryItems)) {
                        for (DictionaryItem item : itemList) {
                            item.setId(dictionaryId);
                        }
                        String[] itemIds = dictionaryItemDao.addItems(itemList);
                        if (!FeepUtil.isNull(itemIds)) {
                            for (int i = 0; i < itemIds.length; i++) {
                                Global.getInstance().getCacheManager().put(CachePool.DICTIONARYITEMCACHE, itemIds[i], itemList.get(i));
                            }
                        }
                    }
                    Global.getInstance().getCacheManager().put(CachePool.DICTIONARYCACHE, dictionaryId, dictionary);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    Global.getInstance().logError("addDictionary  error", e);
                    dictionaryId = null;
                }
                return dictionaryId;
            }
        });
    }

    @Override
    public boolean deleteDictionaryById(String dictionaryId) throws Exception {
        final String id = dictionaryId;
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    dictionaryDao.deleteDictionaryById(id);
                    dictionaryItemDao.deleteItemByDictionaryId(id);
                    Global.getInstance().getCacheManager().remove(CachePool.DICTIONARYCACHE, id);
                    List<DictionaryItem> items = Global.getInstance().getCacheManager().findByAttribute(CachePool.DICTIONARYITEMCACHE, DictionaryItem.pk, id, DictionaryItem.class);
                    if (!FeepUtil.isNull(items)) {
                        String[] itemIds = new String[items.size()];
                        for (int i = 0; i < items.size(); i++) {
                            itemIds[i] = items.get(i).getId();
                        }
                        Global.getInstance().getCacheManager().removeAll(CachePool.DICTIONARYITEMCACHE, itemIds);
                    }
                    return true;
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    Global.getInstance().logError("deleteDictionaryById  error", e);
                    return false;
                }
            }
        });
    }

    @Override
    public boolean updateDictionary(Dictionary dictionaryInfo, List<DictionaryItem> dictionaryItemList) throws Exception {
        final Dictionary dictionary = dictionaryInfo;
        final List<DictionaryItem> newItemList = dictionaryItemList;
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                List<DictionaryItem> newDictionaryItemList = new LinkedList<DictionaryItem>();
                List<String> deleteIds = new LinkedList<String>();
                List<DictionaryItem> modifyDictionaryItemList = new LinkedList<DictionaryItem>();
                try {
                    //1.modify table
                    boolean ret = dictionaryDao.modifyDictionary(dictionary);
                    if (!ret) return false;
                    //2.find items Info
                    List<DictionaryItem> oldItemList = Global.getInstance().getCacheManager().findByAttribute(CachePool.DICTIONARYITEMCACHE, DictionaryItem.pk, dictionary.getId(), DictionaryItem.class);
                    //3.foreach compare field and modify
                    Map<String, DictionaryItem> oldItemMap = convertDictionaryItemsToMap(oldItemList);
                    Map<String, DictionaryItem> newItemMap = convertDictionaryItemsToMap(newItemList);
                    if (!FeepUtil.isNull(oldItemList)) {
                        for (DictionaryItem oldItem : oldItemList) {
                            //add to remove oldItem
                            if (null == newItemMap.get(oldItem.getId())) {
                                deleteIds.add(oldItem.getId());
                            }
                        }
                        //delete item
                        if (!FeepUtil.isNull(deleteIds)) {
                            dictionaryItemDao.deleteItemByIds(deleteIds.toArray(new String[deleteIds.size()]));
                        }
                    }
                    if (!FeepUtil.isNull(newItemList)) {
                        for (DictionaryItem newItem : newItemList) {
                            DictionaryItem oldItem = oldItemMap.get(newItem.getId());
                            //add to newItem
                            if (null == oldItem) {
                                newDictionaryItemList.add(newItem);
                            } else {
                                if (!newItem.equals(oldItem)) {
                                    //modify field info
                                    dictionaryItemDao.udpateItemInfo(newItem);
                                    modifyDictionaryItemList.add(newItem);
                                }
                            }
                        }
                        if (!FeepUtil.isNull(newDictionaryItemList)) {
                            String[] fieldIds = dictionaryItemDao.addItems(newDictionaryItemList);
                            for (int i = 0; i < fieldIds.length; i++) {
                                Global.getInstance().getCacheManager().put(CachePool.DICTIONARYITEMCACHE, fieldIds[i], newDictionaryItemList.get(i));
                            }
                        }
                        if (!FeepUtil.isNull(deleteIds)) {
                            Global.getInstance().getCacheManager().removeAll(CachePool.DICTIONARYITEMCACHE, deleteIds.toArray(new String[deleteIds.size()]));
                        }
                        if (!FeepUtil.isNull(modifyDictionaryItemList)) {
                            for (DictionaryItem item : modifyDictionaryItemList) {
                                Global.getInstance().getCacheManager().update(CachePool.DICTIONARYITEMCACHE, item.getId(), item);
                            }
                        }
                    }
                    Global.getInstance().getCacheManager().update(CachePool.DICTIONARYCACHE, dictionary.getId(), dictionary);
                    return true;
                } catch (Exception e) {
                    Global.getInstance().logError("updateDictionary error", e);
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    private Map<String, DictionaryItem> convertDictionaryItemsToMap(List<DictionaryItem> dictionaryItems) {
        Map<String, DictionaryItem> map = new HashMap<String, DictionaryItem>();
        if (!FeepUtil.isNull(dictionaryItems)) {
            for (DictionaryItem dictionaryItem : dictionaryItems) {
                map.put(dictionaryItem.getId(), dictionaryItem);
            }
        }
        return map;
    }

    @Override
    public EntityBeanSet queryDictionary(FeepQueryBean queryBean) throws Exception {
        try {
            List<Dictionary> dictionaryList = Global.getInstance().getCacheManager().queryCache(CachePool.DICTIONARYCACHE, queryBean, Dictionary.class);
            List<EntityBean> entityBeans = new LinkedList<EntityBean>();
            if (!FeepUtil.isNull(dictionaryList)) {
                for (Dictionary dictionary : dictionaryList) {
                    EntityBean bean = new EntityBean();
                    bean.set("id", dictionary.getId());
                    bean.set("dictionaryname", dictionary.getDictionaryname());
                    bean.set("showname", dictionary.getShowname());
                    bean.set("description", dictionary.getDescription());
                    entityBeans.add(bean);
                }
            }
            EntityBeanSet ebs = new EntityBeanSet(entityBeans);
            ebs.setModuleName(queryBean.getModuleName());
            int count = Global.getInstance().getCacheManager().getSize(CachePool.DICTIONARYCACHE);
            Page page = new Page();
            page.setPageIndex(queryBean.getPageIndex());
            page.setPageSize(queryBean.getPageSize());
            page.setTotalCount(count);
            page.setTotalPageNum(count / queryBean.getPageSize() + 1);
            ebs.setPage(page);
            return ebs;
        } catch (TableException e) {
            throw new Exception("queryDictionary error", e);
        }
    }

    @Override
    public Dictionary findDictionaryById(String id) throws Exception {
        try {
            return (Dictionary) Global.getInstance().getCacheManager().get(CachePool.DICTIONARYCACHE, id);
        } catch (Exception e) {
            throw new Exception("findDictionaryById error", e);
        }
    }

    @Override
    public EntityBeanSet findDictionaryItemsByDictionaryId(String dictionaryId) throws Exception {
        try {
            List<DictionaryItem> list = Global.getInstance().getCacheManager().findByAttribute(CachePool.DICTIONARYITEMCACHE, DictionaryItem.pk, dictionaryId, DictionaryItem.class);
            List<EntityBean> entityBeans = new LinkedList<EntityBean>();
            if (!FeepUtil.isNull(list)) {
                for (DictionaryItem item : list) {
                    EntityBean bean = new EntityBean();
                    bean.set("id", item.getId());
                    bean.set("dictionaryid", item.getDictionaryid());
                    bean.set("childrenid", item.getChildrenid());
                    bean.set("codeid", item.getCodeid());
                    bean.set("codevalue", item.getCodevalue());
                    bean.set("description", item.getDescription());
                    bean.set("sortnum", item.getSortnum());
                    entityBeans.add(bean);
                }
            }
            return new EntityBeanSet(entityBeans);
        } catch (Exception e) {
            throw new Exception("findDictionaryItemsByDictionaryId error, dictionaryId:" + dictionaryId, e);
        }
    }
}
