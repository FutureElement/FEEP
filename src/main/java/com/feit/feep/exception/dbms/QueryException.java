package com.feit.feep.exception.dbms;

import com.feit.feep.exception.FException;

public class QueryException extends FException {

    private static final long serialVersionUID = 1L;

    public QueryException(Throwable cause) {
        super(cause);
    }

    public QueryException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public QueryException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }

}
