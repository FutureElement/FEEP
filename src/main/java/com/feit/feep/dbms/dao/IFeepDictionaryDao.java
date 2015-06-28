package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/23 0023.
 */
public interface IFeepDictionaryDao {

    /**
     * 新增一个字典
     *
     * @param dictionary
     * @return
     * @throws TableException
     */
    String addDictionary(Dictionary dictionary) throws TableException;

    /**
     * 修改字典信息
     *
     * @param dictionary
     * @return
     * @throws TableException
     */
    boolean modifyDictionary(Dictionary dictionary) throws TableException;

    /**
     * 根据id查询字典信息
     *
     * @param id
     * @return
     * @throws TableException
     */
    Dictionary findDictionaryById(String id) throws TableException;

    /**
     * 删除一个字典
     *
     * @param id
     * @return
     * @throws TableException
     */
    boolean deleteDictionaryById(String id) throws TableException;

    /**
     * 查询字典
     *
     * @param queryBean
     * @return
     * @throws TableException
     */
    List<Dictionary> queryDictionary(FeepQueryBean queryBean) throws TableException;

    /**
     * 获得字典总数
     *
     * @return
     * @throws TableException
     */
    int getTotalCount() throws TableException;
}
