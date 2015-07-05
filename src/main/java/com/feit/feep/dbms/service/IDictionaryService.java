package com.feit.feep.dbms.service;

import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.query.FeepQueryBean;

import java.util.List;

/**
 * 数据字典service接口
 * Created by zhanggang on 2015/6/29.
 */
public interface IDictionaryService {

    /**
     * 新增一个数据字典
     *
     * @param dictionary
     * @param itemList
     * @return
     * @throws Exception
     */
    public String addDictionary(Dictionary dictionary, List<DictionaryItem> itemList) throws Exception;

    /**
     * 删除数据字典
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteDictionaryById(String id) throws Exception;

    /**
     * 修改数据字典
     *
     * @param dictionary
     * @param itemList
     * @return
     * @throws Exception
     */
    public boolean updateDictionary(Dictionary dictionary, List<DictionaryItem> itemList) throws Exception;

    /**
     * 查询数据字典
     *
     * @param queryBean
     * @return
     * @throws Exception
     */
    public EntityBeanSet queryDictionary(FeepQueryBean queryBean) throws Exception;

    /**
     * 根据数据字典id获得字典信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Dictionary findDictionaryById(String id) throws Exception;

    /**
     * 根据字典名字查询字典
     *
     * @param dictionaryname
     * @return
     * @throws Exception
     */
    public Dictionary findDictionaryByName(String dictionaryname) throws Exception;

    /**
     * 根据数据字典id获得字典项
     *
     * @param dictionaryId
     * @return
     * @throws Exception
     */
    public EntityBeanSet findDictionaryItemsByDictionaryId(String dictionaryId) throws Exception;

}
