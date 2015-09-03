package com.feit.feep.core.log;

public interface ILog {

    void logInfo(String msg, Class<?> classType);

    void logInfo(Throwable t, Class<?> classType);

    void logInfo(String msg, Throwable t, Class<?> classType);

    void logWarn(String msg, Class<?> classType);

    void logWarn(Throwable t, Class<?> classType);

    void logWarn(String msg, Throwable t, Class<?> classType);

    void logError(String msg, Class<?> classType);

    void logError(Throwable t, Class<?> classType);

    void logError(String msg, Throwable t, Class<?> classType);

    void logFatal(String msg, Class<?> classType);

    void logFatal(Throwable t, Class<?> classType);

    void logFatal(String msg, Throwable t, Class<?> classType);
}
