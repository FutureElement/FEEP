package com.feit.feep.dbms.service;

import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;

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
     * @throws TableException
     */
    public String addDictionary(Dictionary dictionary, List<DictionaryItem> itemList) throws TableException;

    /**
     * 删除数据字典
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteDictionaryById(String id) throws TableException;

    /**
     * 修改数据字典
     *
     * @param dictionary
     * @param itemList
     * @return
     * @throws TableException
     */
    public boolean updateDictionary(Dictionary dictionary, List<DictionaryItem> itemList) throws TableException;

    /**
     * 查询数据字典
     *
     * @param queryBean
     * @return
     * @throws TableException
     */
    public EntityBeanSet queryDictionary(FeepQueryBean queryBean) throws TableException;

    /**
     * 根据数据字典id获得字典信息
     *
     * @param id
     * @return
     * @throws TableException
     */
    public Dictionary findDictionaryById(String id) throws TableException;

    /**
     * 根据数据字典id获得字典项
     *
     * @param dictionaryId
     * @return
     * @throws TableException
     */
    public EntityBeanSet findDictionaryItemsByDictionaryId(String dictionaryId) throws TableException;

}
