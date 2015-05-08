package com.feit.feep.exception.mvc;

import com.feit.feep.exception.FException;

/**
 * Created by zhanggang on 2015/5/6.
 */
public class FeepControllerException extends FException {
    private static final long serialVersionUID = 1L;

    public FeepControllerException(Throwable cause) {
        super(cause);
    }

    public FeepControllerException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public FeepControllerException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }
}
