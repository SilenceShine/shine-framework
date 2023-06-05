package io.github.SilenceShine.shine.util.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 日志打印工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
@Slf4j
public class LogUtil {

    private static final Map<Class<?>, Logger> LOG_CLASS_CACHE = new ConcurrentHashMap<>(16);

    /**
     * 获取object的日志对象
     *
     * @param object 对象
     * @return 日志对象
     */
    public static Logger generateLogger(Object object) {
        // logger
        if (object instanceof Logger) return (Logger) object;
        // string
        if (object instanceof String) return LoggerFactory.getLogger((String) object);
        // object
        Class<?> clazz = object instanceof Class ? (Class<?>) object : object.getClass();
        return LOG_CLASS_CACHE.computeIfAbsent(clazz, LoggerFactory::getLogger);
    }

    public static Object[] params(Object... params) {
        return (params == null || params.length == 0) ? new Object[0] : params;
    }

    public static void info(Object object, String message, Object... params) {
        execute(object, Logger::isInfoEnabled, (logger, objects) -> logger.info(message, objects), () -> params);
    }

    public static void info(Object object, String message, Supplier<Object[]> params) {
        execute(object, Logger::isInfoEnabled, (logger, objects) -> logger.info(message, objects), params);
    }

    public static void warn(Object object, String message, Object... params) {
        execute(object, Logger::isWarnEnabled, (logger, objects) -> logger.warn(message, objects), () -> params);
    }

    public static void warn(Object object, String message, Supplier<Object[]> params) {
        execute(object, Logger::isWarnEnabled, (logger, objects) -> logger.warn(message, objects), params);
    }

    public static void error(Object object, String message, Object... params) {
        execute(object, Logger::isErrorEnabled, (logger, objects) -> logger.error(message, objects), () -> params);
    }

    public static void error(Object object, String message, Supplier<Object[]> params) {
        execute(object, Logger::isErrorEnabled, (logger, objects) -> logger.error(message, objects), params);
    }

    public static void debug(Object object, String message, Object... params) {
        execute(object, Logger::isDebugEnabled, (logger, objects) -> logger.debug(message, objects), () -> params);
    }

    public static void debug(Object object, String message, Supplier<Object[]> params) {
        execute(object, Logger::isDebugEnabled, (logger, objects) -> logger.debug(message, objects), params);
    }

    public static void trace(Object object, String message, Object... params) {
        execute(object, Logger::isTraceEnabled, (logger, objects) -> logger.trace(message, objects), () -> params);
    }

    public static void trace(Object object, String message, Supplier<Object[]> params) {
        execute(object, Logger::isTraceEnabled, (logger, objects) -> logger.trace(message, objects), params);
    }

    private static void execute(Object object, Predicate<Logger> predicate, BiConsumer<Logger, Object[]> invoke, Supplier<Object[]> params) {
        Optional.ofNullable(object)
                .map(LogUtil::generateLogger)
                .filter(predicate)
                .ifPresent(logger -> invoke.accept(logger, params.get()));
    }

}
