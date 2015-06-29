package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 数据字段项DAO接口
 * Created by ZhangGang on 2015/6/28 0028.
 */
public interface IFeepDictionaryItemDao {

    /**
     * 新增一个字典项
     *
     * @param item
     * @return
     * @throws TableException
     */
    public String addItem(DictionaryItem item) throws TableException;

    /**
     * 批量新增字典项
     *
     * @param itemList
     * @return
     * @throws TableException
     */
    public String[] addItems(List<DictionaryItem> itemList) throws TableException;

    /**
     * 根据id删除字典项
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteItemById(String id) throws TableException;

    /**
     * 根据ids批量删除字典项
     *
     * @param ids
     * @return
     * @throws TableException
     */
    public boolean deleteItemByIds(String[] ids) throws TableException;

    /**
     * 根据字典id删除字典项
     *
     * @param parentId
     * @return
     * @throws TableException
     */
    public boolean deleteItemByDictionaryId(String parentId) throws TableException;

    /**
     * 修改字典项
     *
     * @param item
     * @return
     * @throws TableException
     */
    public boolean udpateItemInfo(DictionaryItem item) throws TableException;

    /**
     * 根据id获得字典项信息
     *
     * @param id
     * @return
     * @throws TableException
     */
    public DictionaryItem findDictionaryItemById(String id) throws TableException;

    /**
     * 根据父级id获得字典子项
     *
     * @param parentId
     * @return
     * @throws TableException
     */
    public List<DictionaryItem> findDictionaryChildrenItemsById(String parentId) throws TableException;

    /**
     * 根据字典Id获得字典项
     *
     * @param parentId
     * @return
     * @throws TableException
     */
    public List<DictionaryItem> findDictionaryItemsByDictionaryId(String parentId) throws TableException;

    /**
     * 获得所有字典项
     *
     * @return
     * @throws TableException
     */
    public List<DictionaryItem> findAllItems() throws TableException;

}
