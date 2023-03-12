package io.github.SilenceShine.shine.core.util;

import io.github.SilenceShine.shine.core.exception.BizException;
import io.github.SilenceShine.shine.core.exception.ResultStatus;
import io.github.SilenceShine.shine.core.exception.SysException;

import java.util.function.Supplier;

/**
 * 异常工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
public class ExceptionUtil {

    public static Supplier<? extends BizException> bizException(String message) {
        return bizException(ResultStatus.SERVER_ERROR, message);
    }

    public static Supplier<? extends BizException> bizException(int code, Supplier<String> message) {
        return bizException(code, message.get());
    }

    public static Supplier<? extends BizException> bizException(int code, String message) {
        return () -> new BizException(code, message);
    }

    public static Supplier<? extends SysException> sysException(String message) {
        return sysException(ResultStatus.SERVER_ERROR, message);
    }

    public static Supplier<? extends SysException> sysException(int code, Supplier<String> message) {
        return sysException(code, message.get());
    }

    public static Supplier<? extends SysException> sysException(int code, String message) {
        return () -> new SysException(code, message);
    }

}
