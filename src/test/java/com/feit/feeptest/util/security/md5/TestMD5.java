package com.feit.feeptest.util.security.md5;

import com.feit.feep.core.Global;
import org.junit.Test;

import com.feit.feep.util.security.MD5Util;

import java.security.NoSuchAlgorithmException;

public class TestMD5 {
    
    @Test
    public void test() throws NoSuchAlgorithmException {
        String a = MD5Util.encryption("5377162t");
        Global.getInstance().logInfo(a);
        String b = MD5Util.encryption("5377162t");
        Global.getInstance().logInfo(b);
        String c = MD5Util.encryption("feep2015");
        Global.getInstance().logInfo(c);
    }
}
