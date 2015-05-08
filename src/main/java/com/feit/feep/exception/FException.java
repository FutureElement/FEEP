package com.feit.feep.exception;

public class FException extends Exception {

    private static final long serialVersionUID = -6178902666200309763L;

    public FException(Throwable cause) {
        super(cause);
    }

    public FException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public FException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }
}
