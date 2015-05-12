package com.feit.feeptest.core.loader;

import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.core.loader.xml.FeepSqlMappingLoader;
import com.feit.feep.exception.xml.XmlException;

public class TestXmlLoader extends FeepJUnit {

    @Test
    public void test1() {
        String dbname = Global.getInstance().getFeepConfig().getDBInfo().getDbname();
        Global.getInstance().logInfo(dbname);
        int pageSize = Global.getInstance().getFeepConfig().getDefaultPageSize();
        Global.getInstance().logInfo(pageSize, this.getClass());
        Assert.assertEquals(Global.getInstance().getFeepConfig().isAddUserToCache(), true);
    }

    @Test
    public void test2() throws XmlException {
        URL u = this.getClass().getClassLoader().getResource("FeepResource/sql/system/UserDao.xml");
        FeepSqlMappingLoader dao = new FeepSqlMappingLoader(u.getPath());
        Map<String, String> map = dao.getAllSqls();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Global.getInstance().logInfo(map.get(key));
        }

    }

}
