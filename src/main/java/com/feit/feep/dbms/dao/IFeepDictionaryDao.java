package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryDetail;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/23 0023.
 */
public interface IFeepDictionaryDao {
    String addDictionary(Dictionary dictionary) throws TableException;

    boolean modifyDictionary(Dictionary dictionary) throws TableException;

    Dictionary findDictionaryById(String id) throws TableException;

    boolean deleteDictionaryById(String id) throws TableException;

    List<DictionaryDetail> findDictionaryDetailsByParentId(String parentId) throws TableException;
}
