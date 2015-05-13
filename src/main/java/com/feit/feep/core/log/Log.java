package com.feit.feep.core.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Log implements ILog {

    private static ILog                  instance = new Log();

    private Log() {
    }

    public static ILog getInstance() {
        return instance;
    }

    private Logger getLogger(Class<?> classType) {
        return Logger.getLogger(classType);
    }

    public void logError(Object o, Class<?> classType) {
        getLogger(classType).error(o);
    }

    public void logInfo(Object o, Class<?> classType) {
        getLogger(classType).info(o);
    }

    public void logWarn(Object o, Class<?> classType) {
        getLogger(classType).warn(o);
    }

    public void logFatal(Object o, Class<?> classType) {
        getLogger(classType).fatal(o);
    }

    @Override
    public void logInfo(Object o, Class<?> classType, Throwable t) {
        getLogger(classType).info(o, t);
    }

    @Override
    public void logWarn(Object o, Class<?> classType, Throwable t) {
        getLogger(classType).warn(o, t);
    }

    @Override
    public void logError(Object o, Class<?> classType, Throwable t) {
        getLogger(classType).error(o, t);
    }

    @Override
    public void logFatal(Object o, Class<?> classType, Throwable t) {
        getLogger(classType).fatal(o, t);
    }
}
