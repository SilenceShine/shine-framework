package shine.framework.core.exception;

import java.io.Serial;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class BizException extends BaseException {

    @Serial
    private static final long serialVersionUID = -2899973784950896928L;

    public BizException(ExceptionEnum exceptionEnum, Object... args) {
        super(exceptionEnum, args);
    }

    public BizException(String message, Object... args) {
        super(message, args);
    }

    public BizException(Integer code, String message, Object... args) {
        super(code, message, args);
    }

}
