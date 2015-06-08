package com.feit.feep.exception.dbms;

import com.feit.feep.exception.FException;

/**
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class TableException extends FException {

    private static final long serialVersionUID = 1L;

    public TableException(Throwable cause) {
        super(cause);
    }

    public TableException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public TableException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }
}
