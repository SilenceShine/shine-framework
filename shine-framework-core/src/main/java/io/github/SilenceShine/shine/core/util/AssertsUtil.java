package io.github.SilenceShine.shine.core.util;

import io.github.SilenceShine.shine.core.exception.BizException;
import io.github.SilenceShine.shine.core.exception.ResultStatus;
import io.github.SilenceShine.shine.core.exception.ExceptionEnum;
import io.github.SilenceShine.shine.util.core.ObjectUtils;

import java.util.function.Supplier;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class AssertsUtil {

    public static void isTrue(boolean expression, Supplier<String> message) throws BizException {
        isTrue(expression, ResultStatus.SERVER_ERROR, message);
    }

    public static void isTrue(boolean expression, ExceptionEnum exceptionEnum) throws BizException {
        isTrue(expression, exceptionEnum.code(), exceptionEnum::message);
    }

    public static void isTrue(boolean expression, int code, Supplier<String> message) throws BizException {
        if (!expression) throw new BizException(code, message.get());
    }

    public static void isFalse(boolean expression, Supplier<String> message) {
        isFalse(expression, ResultStatus.SERVER_ERROR, message);
    }

    public static void isFalse(boolean expression, ExceptionEnum exceptionEnum) {
        isFalse(expression, exceptionEnum.code(), exceptionEnum::message);
    }

    public static void isFalse(boolean expression, int code, Supplier<String> message) {
        if (expression) throw new BizException(code, message.get());
    }

    public static <T> T isNotNull(T object, Supplier<String> message) {
        return isNotNull(object, ResultStatus.SERVER_ERROR, message);
    }

    public static <T> T isNotNull(T object, ExceptionEnum exceptionEnum) {
        return isNotNull(object, exceptionEnum.code(), exceptionEnum::message);
    }

    public static <T> T isNotNull(T object, int code, Supplier<String> message) {
        if (ObjectUtils.isNull(object)) throw new BizException(code, message.get());
        return object;
    }

    public static <T> T isNull(T object, Supplier<String> message) {
        return isNull(object, ResultStatus.SERVER_ERROR, message);
    }

    public static <T> T isNull(T object, int code, Supplier<String> message) {
        if (ObjectUtils.isNotNull(object)) throw new BizException(code, message.get());
        return object;
    }

    public static <T> T isNotEmpty(T object, Supplier<String> message) {
        return isNotEmpty(object, ResultStatus.SERVER_ERROR, message);
    }

    public static <T> T isNotEmpty(T object, int code, Supplier<String> message) {
        if (ObjectUtils.isEmpty(object)) throw new BizException(code, message.get());
        return object;
    }

    public static <T> T isEmpty(T object, Supplier<String> message) {
        return isEmpty(object, ResultStatus.SERVER_ERROR, message);
    }

    public static <T> T isEmpty(T object, int code, Supplier<String> message) {
        if (ObjectUtils.isNotEmpty(object)) throw new BizException(message.get());
        return object;
    }

}
