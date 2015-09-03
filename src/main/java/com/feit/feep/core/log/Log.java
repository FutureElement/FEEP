package com.feit.feep.core.log;

import com.feit.feep.core.Global;
import org.apache.log4j.Logger;

public class Log implements ILog {

    private static ILog instance = new Log();

    private Log() {
    }

    public static ILog getInstance() {
        return instance;
    }

    private Logger getLogger(Class<?> classType) {
        return Logger.getLogger(classType);
    }

    private void printThrowable(Throwable t) {
        if (Global.getInstance().getFeepConfig().isDevMode()) {
            t.printStackTrace();
        }
    }

    @Override
    public void logInfo(String msg, Class<?> classType) {
        getLogger(classType).info(msg);
    }

    @Override
    public void logInfo(Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).info(t);
    }

    @Override
    public void logInfo(String msg, Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).info(msg, t);
    }

    @Override
    public void logWarn(String msg, Class<?> classType) {
        getLogger(classType).warn(msg);
    }

    @Override
    public void logWarn(Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).warn(t);
    }

    @Override
    public void logWarn(String msg, Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).warn(msg, t);
    }

    @Override
    public void logError(String msg, Class<?> classType) {
        getLogger(classType).error(msg);
    }

    @Override
    public void logError(Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).error(t);
    }

    @Override
    public void logError(String msg, Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).error(msg, t);
    }

    @Override
    public void logFatal(String msg, Class<?> classType) {
        getLogger(classType).fatal(msg);
    }

    @Override
    public void logFatal(Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).fatal(t);
    }

    @Override
    public void logFatal(String msg, Throwable t, Class<?> classType) {
        printThrowable(t);
        getLogger(classType).fatal(msg, t);
    }
}
