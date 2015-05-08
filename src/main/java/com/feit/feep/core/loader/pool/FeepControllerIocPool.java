package com.feit.feep.core.loader.pool;

import java.lang.reflect.Method;

/**
 * 服务控制器容器
 * 
 * @author ZhangGang
 *
 */
public class FeepControllerIocPool extends DefaultIocPool<Method> {

    private static FeepControllerIocPool instance = new FeepControllerIocPool();

    private FeepControllerIocPool() {
        super();
    }

    public static FeepControllerIocPool getInstance() {
        return instance;
    }
}
