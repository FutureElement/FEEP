package com.feit.feep.dbms.table;

import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * ���ݱ�dao�ӿ�
 * Created by zhanggang on 2015/5/15.
 */
public interface IBasicTableDao {

    /**
     * ������
     *
     * @param feepTable ���ݱ���Ϣ
     * @return
     * @throws TableException
     */
    String createTable(FeepTable feepTable) throws TableException;

    /**
     * ����id��ȡ���ݱ���Ϣ
     *
     * @param id
     * @return
     * @throws TableException
     */
    FeepTable getTableById(String id) throws TableException;

    /**
     * �޸����ݱ�
     *
     * @param table
     * @return
     * @throws TableException
     */
    boolean modifyTable(FeepTable table) throws TableException;

    /**
     * ɾ�����ݱ�
     *
     * @param id
     * @return
     * @throws TableException
     */
    boolean deleteTableById(String id) throws TableException;


    /**
     * ��ѯ���ݱ�
     *
     * @param feepQueryBean
     * @return
     * @throws TableException
     */
    List<FeepTable> queryFeepTable(FeepQueryBean feepQueryBean) throws TableException;

}
