package com.feit.feep.exception.json;

import com.feit.feep.exception.FException;

public class JsonException extends FException {

    private static final long serialVersionUID = 1L;

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public JsonException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }

}
