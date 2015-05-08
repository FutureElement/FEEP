package com.feit.feep.exception.xml;

import com.feit.feep.exception.FException;

public class XmlException extends FException {

    private static final long serialVersionUID = 1L;

    public XmlException(Throwable cause) {
        super(cause);
    }

    public XmlException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public XmlException(Throwable cause, String fmt, Object... args) {
        super(String.format(fmt, args), cause);
    }
}
