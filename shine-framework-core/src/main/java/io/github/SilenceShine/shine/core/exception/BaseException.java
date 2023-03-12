package io.github.SilenceShine.shine.core.exception;

import java.io.Serial;
import java.util.Arrays;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String TEMPLATE = "code:[%s] message:[%s]";
    private final int code;
    private final String message;
    private final Object[] args;

    public BaseException(ExceptionEnum exceptionEnum, Object... args) {
        this(exceptionEnum.code(), exceptionEnum.message(), args);
    }

    public BaseException(String message, Object... args) {
        this(ResultStatus.SERVER_ERROR, message, args);
    }

    public BaseException(int code, String message, Object... args) {
        super(buildRealMessage(code, message, args));
        this.code = code;
        this.message = message;
        this.args = args;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return buildRealMessage(code, message, args);
    }

    private static String buildRealMessage(int code, String message, Object... args) {
        String finalMessage = String.format(TEMPLATE, code, message);
        return args.length > 0 ? String.format(finalMessage, Arrays.toString(args)) : finalMessage;
    }

}
