package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.dao.IFeepDictionaryItemDao;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * 字典项dao 实现类
 * Created by zhanggang on 2015/6/29.
 */
@Repository
public class FeepDictionaryItemDao implements IFeepDictionaryItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String KEY_ADDITEM = "sql.dbms.dictionaryItem.addItem";
    private static final String KEY_DELETEITEMBYID = "sql.dbms.dictionaryItem.deleteItemById";
    private static final String KEY_DELETEITEMBYIDS = "sql.dbms.dictionaryItem.deleteItemByIds";
    private static final String KEY_DELETEITEMBYDICTIONARYID = "sql.dbms.dictionaryItem.deleteItemByDictionaryId";
    private static final String KEY_UDPATEITEMINFO = "sql.dbms.dictionaryItem.udpateItemInfo";
    private static final String KEY_FINDDICTIONARYITEMBYID = "sql.dbms.dictionaryItem.findDictionaryItemById";
    private static final String KEY_FINDDICTIONARYCHILDRENITEMSBYID = "sql.dbms.dictionaryItem.findDictionaryChildrenItemsById";
    private static final String KEY_FINDDICTIONARYITEMSBYDICTIONARYID = "sql.dbms.dictionaryItem.findDictionaryItemsByDictionaryId";
    private static final String KEY_FINDALLITEMS = "sql.dbms.dictionaryItem.findAllItems";

    @Override
    public String addItem(DictionaryItem item) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDITEM);
            if (FeepUtil.isNull(item.getId())) item.setId(FeepUtil.getUUID());
            jdbcTemplate.update(sql, convertDictionaryItemToParameterForInsert(item));
            return item.getId();
        } catch (Exception e) {
            throw new TableException("addItem [" + item.getCodevalue() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertDictionaryItemToParameterForInsert(DictionaryItem item) {
        return new Object[]{item.getId(), item.getCodeid(), item.getCodevalue(), item.getSortnum(), item.getDescription(), item.getDictionaryid(), item.getChildrenid()};
    }

    private Object[] convertDictionaryItemToParameterForUpdate(DictionaryItem item) {
        return new Object[]{item.getCodeid(), item.getCodevalue(), item.getSortnum(), item.getDescription(), item.getDictionaryid(), item.getChildrenid(), item.getId()};
    }

    @Override
    public String[] addItems(List<DictionaryItem> itemList) throws TableException {
        try {
            if (!FeepUtil.isNull(itemList)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDITEM);
                List<Object[]> data = new LinkedList<Object[]>();
                List<String> ids = new LinkedList<String>();
                for (DictionaryItem item : itemList) {
                    if (FeepUtil.isNull(item.getId())) item.setId(FeepUtil.getUUID());
                    data.add(convertDictionaryItemToParameterForInsert(item));
                    ids.add(item.getId());
                }
                jdbcTemplate.batchUpdate(sql, data);
                return ids.toArray(new String[ids.size()]);
            }
            return null;
        } catch (Exception e) {
            throw new TableException("addItems [" + itemList.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteItemById(String id) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEITEMBYID);
            int count = jdbcTemplate.update(sql, id);
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteItemById error ,id:" + id, e);
        }
    }

    @Override
    public boolean deleteItemByIds(String[] ids) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEITEMBYIDS);
            int count = jdbcTemplate.update(sql + " (" + GeneratorSqlBuild.convertArrayToSqlString(ids) + ")");
            return count == ids.length;
        } catch (Exception e) {
            throw new TableException("deleteItemByIds error ,ids:" + FeepUtil.toString(ids), e);
        }
    }

    @Override
    public boolean deleteItemByDictionaryId(String dictionaryId) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEITEMBYDICTIONARYID);
            jdbcTemplate.update(sql, dictionaryId);
            return true;
        } catch (Exception e) {
            throw new TableException("deleteItemByDictionaryId error, dictionaryId:" + dictionaryId, e);
        }
    }

    @Override
    public boolean udpateItemInfo(DictionaryItem item) throws TableException {
        try {
            int i = 0;
            if (null != item && !FeepUtil.isNull(item.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UDPATEITEMINFO);
                i = jdbcTemplate.update(sql, convertDictionaryItemToParameterForUpdate(item));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("udpateItemInfo error", e);
            throw new TableException(e);
        }
    }

    @Override
    public boolean batchUdpateItemInfo(List<DictionaryItem> dictionaryItems) throws TableException {
        try {
            if (!FeepUtil.isNull(dictionaryItems)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UDPATEITEMINFO);
                List<Object[]> data = new LinkedList<Object[]>();
                for (DictionaryItem item : dictionaryItems) {
                    data.add(convertDictionaryItemToParameterForUpdate(item));
                }
                jdbcTemplate.batchUpdate(sql, data);
            }
            return true;
        } catch (Exception e) {
            throw new TableException("batchUdpateItemInfo [" + dictionaryItems.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public DictionaryItem findDictionaryItemById(String id) throws TableException {
        DictionaryItem item = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDDICTIONARYITEMBYID);
            List<DictionaryItem> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getInstance(DictionaryItem.class));
            if (null != result) {
                item = result.get(0);
            }
            return item;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public List<DictionaryItem> findDictionaryChildrenItemsById(String id) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDDICTIONARYCHILDRENITEMSBYID);
            List<DictionaryItem> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getInstance(DictionaryItem.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("findDictionaryChildrenItemsById error, id:" + id, e);
        }
    }

    @Override
    public List<DictionaryItem> findDictionaryItemsByDictionaryId(String dictionaryId) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDDICTIONARYITEMSBYDICTIONARYID);
            List<DictionaryItem> result = jdbcTemplate.query(sql, new Object[]{dictionaryId}, FeepEntityRowMapper.getInstance(DictionaryItem.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("findDictionaryItemsByDictionaryId error, dictionaryId:" + dictionaryId, e);
        }
    }

    @Override
    public List<DictionaryItem> findAllItems() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDALLITEMS);
            List<DictionaryItem> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getInstance(DictionaryItem.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("findAllItems error", e);
        }
    }
}
