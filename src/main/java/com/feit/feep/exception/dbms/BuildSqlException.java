package com.feit.feep.exception.dbms;


public class BuildSqlException extends QueryException {

    private static final long serialVersionUID = 1L;

    public BuildSqlException(Throwable cause) {
        super(cause);
    }

    public BuildSqlException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public BuildSqlException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }

}
