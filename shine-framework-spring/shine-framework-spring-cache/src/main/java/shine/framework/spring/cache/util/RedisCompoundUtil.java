package shine.framework.spring.cache.util;

import java.util.concurrent.TimeUnit;

/**
 * redis组合命令工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
public class RedisCompoundUtil extends RedisUtil {

    /**
     * 写入hash
     *
     * @param key     键
     * @param hashKey key
     * @param value   值
     * @param <HV>    值类型
     */
    public synchronized static <HV> void hPut(String key, String hashKey, HV value, long timeout, TimeUnit unit) {
        hPut(key, hashKey, value);
        expire(key, timeout, unit);
    }

}
