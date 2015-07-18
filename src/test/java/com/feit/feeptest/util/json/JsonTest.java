package com.feit.feeptest.util.json;

import java.lang.reflect.*;
import java.util.*;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.impl.FeepTableDao;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.exception.json.JsonException;

import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.feit.feep.util.json.FeepJsonUtil;

public class JsonTest {

    @Test
    public void test2() throws JsonException {
        long l1 = System.currentTimeMillis();
        Map<String, String> m = new HashMap<String, String>();
        for (int i = 0; i < 50; i++) {
            m.put(i + "", "aaaaaaaaaaaaaaa");
        }
        List<Map<String, String>> list = new LinkedList<Map<String, String>>();
        list.add(m);
        long l2 = System.currentTimeMillis();
        Global.getInstance().logInfo("build cost: " + (l2 - l1));
        for (int i = 1; i <= 10; i++) {
            Global.getInstance().logInfo("---------" + i + "---------");
            long l3 = System.currentTimeMillis();
            String a = FeepJsonUtil.toJson(list);
            Global.getInstance().logInfo(a);
            long l4 = System.currentTimeMillis();
            List<Map<String, String>> list2 = FeepJsonUtil.parseJson(a, new TypeReference<List<Map<String, String>>>() {
            });
            long l5 = System.currentTimeMillis();
            Global.getInstance().logInfo("fastjson toJson cost: " + (l4 - l3));
            Global.getInstance().logInfo("fastjson parseJson cost: " + (l5 - l4));
            Global.getInstance().logInfo(a);
            Global.getInstance().logInfo(list2.get(0).get("0"));
        }
    }

    @Test
    public void testParseJSON() throws JsonException {
        long s1 = System.currentTimeMillis();
        List<FeepTableField> list1 = new LinkedList<FeepTableField>();
        for (int i = 0; i < 100; i++) {
            FeepTableField field = new FeepTableField();
            field.setId("#" + i);
            field.setDatatype("0");
            field.setName("test" + i);
            field.setNotnull(false);
            field.setUnique(false);
            field.setRange(100);
            field.setPrecision(0);
            field.setTableid("001");
            field.setShowname("showname" + i);
            list1.add(field);
        }
        String json = FeepJsonUtil.toJson(list1);
        long e1 = System.currentTimeMillis();
        Global.getInstance().logInfo(json);
        Global.getInstance().logInfo("###########cost1############:" + (e1 - s1));
        long s2 = System.currentTimeMillis();
        List<FeepTableField> list2 = FeepJsonUtil.parseJson(json, new TypeReference<List<FeepTableField>>() {
        });
        long e2 = System.currentTimeMillis();
        Global.getInstance().logInfo("###########cost2############:" + (e2 - s2));
        Global.getInstance().logInfo(list2.size());
        Global.getInstance().logInfo(list2.get(list2.size() - 1).getName());
    }

    @SuppressWarnings("unchecked")
	public void test3() throws Exception {
        List<FeepTableField> list = new LinkedList<FeepTableField>();
        for (int i = 0; i < 10; i++) {
            FeepTableField field = new FeepTableField();
            field.setId("#" + i);
            field.setDatatype("0");
            field.setName("test" + i);
            field.setNotnull(false);
            field.setUnique(false);
            field.setRange(100);
            field.setPrecision(0);
            field.setTableid("001");
            field.setShowname("showname" + i);
            list.add(field);
        }
        String listJson = FeepJsonUtil.toJson(list);
        FeepTable table = new FeepTable();
        table.setName("table1");
        table.setId("001");
        table.setShowname("testTable");
        table.setDatasourceid("0");
        table.setTabletype("1");
        String tableJson = FeepJsonUtil.toJson(table);
        String parameters = "[" + tableJson + "," + listJson + "]";
        Global.getInstance().logInfo(parameters);
        Class<FeepTableDao> feepTableDaoClass = FeepTableDao.class;
        Method createTable = feepTableDaoClass.getMethod("createTable", FeepTable.class, List.class);
        createTable.getParameterCount();
        Type[] types = createTable.getGenericParameterTypes();
        Object[] objs = FeepJsonUtil.parseArrayForDifferentTypes(parameters, types);
        Global.getInstance().logInfo(((FeepTable) objs[0]).getName());
        Global.getInstance().logInfo(((List<FeepTableField>) objs[1]).get(5).getShowname());
    }

    @Test
    public void test4() throws Exception {
        Method me = JsonTest.class.getMethod("test3");
        me.invoke(new JsonTest());
    }


}
