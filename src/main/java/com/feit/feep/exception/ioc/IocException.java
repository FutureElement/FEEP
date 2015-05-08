package com.feit.feep.exception.ioc;

import com.feit.feep.exception.FException;

public class IocException extends FException {

    private static final long serialVersionUID = 1L;

    public IocException(Throwable cause) {
        super(cause);
    }

    public IocException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public IocException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }
}
