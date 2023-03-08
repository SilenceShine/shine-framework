package shine.framework.spring.cache.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * redis工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
public class RedisUtil {

    public static long timeout = 1000L;
    // 单位秒
    public static long scan_count = 1000L;
    public static long scan_timeout = -1;
    public static TimeUnit unit = TimeUnit.SECONDS;
    public static ChronoUnit chronoUnit = ChronoUnit.SECONDS;
    private static RedisTemplate redisTemplate;
    public static Function<String, String> keyTrans = s -> s;

    public static <V> ValueOperations<String, V> valueOp() {
        return redisTemplate.opsForValue();
    }

    public static <V> HashOperations<String, String, V> hashOp() {
        return redisTemplate.opsForHash();
    }

    //  ------------------------------------------- template -----------------------------------------------------

    /**
     * 是否存在key
     *
     * @param key 键
     * @return 是否存在key
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 模糊查询key列表
     * 例如要查询A:B:C,A:B:D
     * 则传入"A:B:*"
     * 注意扫描大数据量的时候要慎用 使用{@link this#scan(String, Long, DataType)}。少量没问题。
     *
     * @param pattern 模糊匹配
     * @return 键值
     */
    public static Set<String> keys(String pattern) {
        Set<String> values = redisTemplate.keys(keyTrans.apply(pattern));
        return values == null ? Collections.emptySet() : values;
    }

    public static Set<String> keysByScan(String pattern) {
        return keysByScan(pattern, scan_count, DataType.NONE);
    }

    public static Set<String> keysByScan(String pattern, DataType type) {
        return keysByScan(pattern, scan_count, type);
    }

    public static Set<String> keysByScan(String pattern, Long count) {
        return keysByScan(pattern, count, DataType.NONE);
    }

    public static Set<String> keysByScan(String pattern, Long count, DataType type) {
        return keysByScan(pattern, count, type, scan_timeout);
    }

    /**
     * 获取keys
     *
     * @param pattern 正则
     * @param count   条数
     * @param type    类型
     * @param timeout 获取超时时间
     * @return keys集合
     */
    public static Set<String> keysByScan(String pattern, Long count, DataType type, Long timeout) {
        ScanOptions options = ScanOptions.scanOptions().match(keyTrans.apply(pattern)).count(count).type(type).build();
        if (null != options.getCount() && options.getCount() <= 0) return new HashSet<>();
        Set<String> keys = new HashSet<>();
        try (Cursor<String> cursor = scan(options)) {
            long start = System.currentTimeMillis();
            while (cursor.hasNext()) {
                if (timeout > 0 && System.currentTimeMillis() - start > timeout) break;
                keys.add(cursor.next());
            }
        }
        return keys;
    }

    public static Cursor<String> scan(String pattern) {
        return scan(pattern, scan_count, DataType.NONE);
    }

    public static Cursor<String> scan(String pattern, Long count) {
        return scan(pattern, count, DataType.NONE);
    }

    /**
     * 扫描redis key
     *
     * @param pattern 模糊匹配
     * @param count   数量
     * @param type    类型
     * @return Cursor<String>
     */
    public static Cursor<String> scan(String pattern, Long count, DataType type) {
        ScanOptions options = ScanOptions.scanOptions()
                .type(type)
                .count(count)
                .match(pattern)
                .build();
        return scan(options);
    }

    /**
     * 扫描redis key
     *
     * @param options 参数
     * @return 键值
     */
    public static Cursor<String> scan(ScanOptions options) {
        return redisTemplate.scan(copyScanOptions(options, keyTrans));
    }

    /**
     * 更新key的过期时间
     *
     * @param key 键
     */
    public static void expire(String key) {
        redisTemplate.expire(keyTrans.apply(key), timeout, unit);
    }

    /**
     * 更新key的过期时间
     *
     * @param key 键
     */
    public static void expire(String key, long timeout) {
        redisTemplate.expire(keyTrans.apply(key), timeout, unit);
    }

    /**
     * 更新key的过期时间
     *
     * @param key 键
     */
    public static void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(keyTrans.apply(key), timeout, unit);
    }

    /**
     * 删除缓存
     *
     * @param key 键
     */
    public static void delete(String key) {
        redisTemplate.delete(keyTrans.apply(key));
    }

    /**
     * 删除缓存
     *
     * @param keys 键
     */
    public static void delete(Collection<String> keys) {
        redisTemplate.delete(keys.stream().map(keyTrans).collect(Collectors.toList()));
    }

    //  ------------------------------------------- value -----------------------------------------------------

    /**
     * 设置一个不过其的key
     *
     * @param key   键
     * @param value 值
     * @param <V>   类型
     */
    public static <V> void set(String key, V value) {
        valueOp().set(keyTrans.apply(key), value);
    }

    public static <V> void setDefaultExpire(String key, V value) {
        set(key, value, timeout);
    }

    public static <V> void set(String key, V value, long expire) {
        set(key, value, Duration.of(expire, chronoUnit));
    }

    /**
     * 写入redis 带过期时间
     *
     * @param key      键
     * @param value    值
     * @param duration 过期时间
     * @param <V>      类型
     */
    public static <V> void set(String key, V value, Duration duration) {
        valueOp().set(key, value, duration);
    }

    /**
     * 写入redis
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间(s)
     * @param unit   时间单位
     * @param <V>    类型
     */
    public static <V> void set(String key, V value, long expire, TimeUnit unit) {
        valueOp().set(keyTrans.apply(key), value, expire, unit);
    }

    /**
     * 获取redis值
     *
     * @param key 键
     * @param <V> 类型
     * @return <V>
     */
    public static <V> V get(String key) {
        ValueOperations<String, V> value = valueOp();
        return value.get(keyTrans.apply(key));
    }

    /**
     * 获取redis值并且写入新的值
     *
     * @param key 键
     * @param <V> 类型
     * @return <V>
     */
    public static <V> V getAndSet(String key, V value) {
        ValueOperations<String, V> valueOp = valueOp();
        return valueOp.getAndSet(keyTrans.apply(key), value);
    }

    /**
     * 获取redis值并且清除过期时间
     *
     * @param key 键
     * @param <V> 类型
     * @return <V>
     */
    public static <V> V getAndPersist(String key) {
        ValueOperations<String, V> valueOp = valueOp();
        return valueOp.getAndPersist(keyTrans.apply(key));
    }

    /**
     * 获取redis值并且删除
     *
     * @param key 键
     * @param <V> 类型
     * @return <V>
     */
    public static <V> V getAndDelete(String key) {
        ValueOperations<String, V> value = valueOp();
        return value.getAndDelete(keyTrans.apply(key));
    }

    /**
     * 获取redis值并且续期
     *
     * @param key 键
     * @param <V> 类型
     * @return <V>
     */
    public static <V> V getAndExpire(String key, Duration timeout) {
        ValueOperations<String, V> value = valueOp();
        return value.getAndExpire(keyTrans.apply(key), timeout);
    }

    /**
     * 获取redis值并且续期
     *
     * @param key 键
     * @param <V> 类型
     * @return <V>
     */
    public static <V> V getAndExpire(String key, long timeout, TimeUnit unit) {
        ValueOperations<String, V> value = valueOp();
        return value.getAndExpire(keyTrans.apply(key), timeout, unit);
    }

    //  ------------------------------------------- hash -----------------------------------------------------

    /**
     * 写入hash
     *
     * @param key     键
     * @param hashKey key
     * @param value   值
     * @param <HV>    值类型
     */
    public static <HV> void hPut(String key, String hashKey, HV value) {
        hashOp().put(keyTrans.apply(key), hashKey, value);
    }

    /**
     * 写入hash
     *
     * @param key  键
     * @param m    map值
     * @param <HV> 值类型
     */
    public static <HV> void hPutAll(String key, Map<String, ? extends HV> m) {
        hashOp().putAll(keyTrans.apply(key), m);
    }

    /**
     * 删除hash key
     *
     * @param key      键
     * @param hashKeys hashKey集合
     * @return 删除的个数
     */
    public static Long hDelete(String key, String... hashKeys) {
        return hashOp().delete(keyTrans.apply(key), (Object) hashKeys);
    }

    /**
     * 判断hashKey 是否存在
     *
     * @param key     键
     * @param hashKey hashKey
     * @return 是否存在
     */
    public static Boolean hHasKey(String key, String hashKey) {
        return hashOp().hasKey(keyTrans.apply(key), hashKey);
    }

    /**
     * 获取hash值
     *
     * @param key     键
     * @param hashKey map值
     * @param <HV>    值类型
     */
    public static <HV> HV hGet(String key, String hashKey) {
        HashOperations<String, String, HV> hash = hashOp();
        return hash.get(keyTrans.apply(key), hashKey);
    }

    //  ------------------------------------------- util -----------------------------------------------------

    /**
     * 拷贝ScanOptions
     *
     * @param options  options
     * @param function key 转换参数
     * @return ScanOptions
     */
    public static ScanOptions copyScanOptions(ScanOptions options, Function<String, String> function) {
        ScanOptions.ScanOptionsBuilder builder = ScanOptions.scanOptions();
        Optional.ofNullable(options).ifPresent(op -> {
            Optional.ofNullable(op.getPattern()).map(function).ifPresent(builder::match);
            Optional.ofNullable(op.getCount()).ifPresent(builder::count);
            if (op instanceof KeyScanOptions keyScanOptions) {
                Optional.ofNullable(keyScanOptions.getType()).ifPresent(builder::type);
            }
        });
        return builder.build();
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

}
