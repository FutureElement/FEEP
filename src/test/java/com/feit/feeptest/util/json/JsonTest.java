package com.feit.feeptest.util.json;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.feit.feep.core.Global;
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
            List<Map<String, String>> list2 = FeepJsonUtil.parseJson(a, new TypeReference<List<Map<String, String>>>() {});
            long l5 = System.currentTimeMillis();
            Global.getInstance().logInfo("fastjson toJson cost: " + (l4 - l3));
            Global.getInstance().logInfo("fastjson parseJson cost: " + (l5 - l4));
            Global.getInstance().logInfo(a);
            Global.getInstance().logInfo(list2.get(0).get("0"));
        }
    }
}
