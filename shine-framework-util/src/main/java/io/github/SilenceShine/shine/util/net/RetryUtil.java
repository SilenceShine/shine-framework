package io.github.SilenceShine.shine.util.net;

import io.github.SilenceShine.shine.util.log.LogUtil;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 重试工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
public class RetryUtil {

    /**
     * 重试工具类
     *
     * @param supplier 提供一个执行方法
     * @param times    重试次数
     * @param interval 间隔时间 默认秒
     * @param <T>      返回值类型
     * @return 返回值
     */
    public static <T> T retry(Supplier<T> supplier, int times, int interval) {
        for (int i = 0; i < times - 1; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                LogUtil.error(RetryUtil.class, "retry error :{}", () -> LogUtil.params(e.getMessage()));
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return supplier.get();
    }

    /**
     * 重试工具类
     *
     * @param supplier  提供一个执行方法
     * @param times     重试次数
     * @param predicate 重试条件
     * @param <T>       返回值类型
     * @return 返回值
     */
    public static <T> T retry(Supplier<T> supplier, int times, Predicate<T> predicate) {
        T t = supplier.get();
        if (predicate.test(t)) {
            return t;
        }
        for (int i = 0; i < times - 1; i++) {
            t = supplier.get();
            if (predicate.test(t)) {
                return t;
            }
        }
        return t;
    }

}
