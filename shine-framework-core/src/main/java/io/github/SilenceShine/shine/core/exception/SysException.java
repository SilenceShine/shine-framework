package io.github.SilenceShine.shine.core.exception;

import java.io.Serial;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class SysException extends BaseException {

    @Serial
    private static final long serialVersionUID = 6671951457612127584L;

    public SysException(ExceptionEnum exceptionEnum, Object... args) {
        super(exceptionEnum, args);
    }

    public SysException(String message, Object... args) {
        super(message, args);
    }

    public SysException(Integer code, String message, Object... args) {
        super(code, message, args);
    }

}
