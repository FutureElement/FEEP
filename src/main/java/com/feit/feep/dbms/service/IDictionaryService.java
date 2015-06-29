package com.feit.feep.dbms.service;

import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * �����ֵ�service�ӿ�
 * Created by zhanggang on 2015/6/29.
 */
public interface IDictionaryService {

    /**
     * ����һ�������ֵ�
     *
     * @param dictionary
     * @param itemList
     * @return
     * @throws TableException
     */
    public String addDictionary(Dictionary dictionary, List<DictionaryItem> itemList) throws TableException;

    /**
     * ɾ�������ֵ�
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteDictionaryById(String id) throws TableException;

    /**
     * �޸������ֵ�
     *
     * @param dictionary
     * @param itemList
     * @return
     * @throws TableException
     */
    public boolean updateDictionary(Dictionary dictionary, List<DictionaryItem> itemList) throws TableException;

    /**
     * ��ѯ�����ֵ�
     *
     * @param queryBean
     * @return
     * @throws TableException
     */
    public EntityBeanSet queryDictionary(FeepQueryBean queryBean) throws TableException;

    /**
     * ���������ֵ�id����ֵ���Ϣ
     *
     * @param id
     * @return
     * @throws TableException
     */
    public Dictionary findDictionaryById(String id) throws TableException;

    /**
     * ���������ֵ�id����ֵ���
     *
     * @param dictionaryId
     * @return
     * @throws TableException
     */
    public EntityBeanSet findDictionaryItemsByDictionaryId(String dictionaryId) throws TableException;

}
