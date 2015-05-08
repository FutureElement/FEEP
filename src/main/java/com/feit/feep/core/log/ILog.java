package com.feit.feep.core.log;

public interface ILog {

    void logInfo(Object o, Class<?> classType);

    void logInfo(Object o, Class<?> classType, Throwable t);

    void logWarn(Object o, Class<?> classType);

    void logWarn(Object o, Class<?> classType, Throwable t);

    void logError(Object o, Class<?> classType);

    void logError(Object o, Class<?> classType, Throwable t);

    void logFatal(Object o, Class<?> classType);

    void logFatal(Object o, Class<?> classType, Throwable t);
}
