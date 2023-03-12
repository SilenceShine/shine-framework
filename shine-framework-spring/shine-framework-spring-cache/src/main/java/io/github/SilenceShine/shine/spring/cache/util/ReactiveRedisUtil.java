package io.github.SilenceShine.shine.spring.cache.util;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.core.ScanOptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * 响应式redis工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ReactiveRedisUtil {

    public static long timeout = 1000L;
    public static long scan_count = 1000L;
    public static ChronoUnit chrono_unit = ChronoUnit.SECONDS;
    private static ReactiveRedisTemplate reactiveRedisTemplate;

    public static <V> ReactiveValueOperations<String, V> valueOp() {
        return reactiveRedisTemplate.opsForValue();
    }

    public static <V> ReactiveHashOperations<String, String, V> hashOp() {
        return reactiveRedisTemplate.opsForHash();
    }

    //  ------------------------------------------- template -----------------------------------------------------

    /**
     * 是否存在key
     *
     * @param key 键
     * @return Mono<Boolean>
     */
    public static Mono<Boolean> hasKey(String key) {
        return reactiveRedisTemplate.hasKey(key);
    }

    /**
     * 模糊查询key列表
     * 例如要查询A:B:C,A:B:D
     * 则传入"A:B:*"
     * 注意扫描大数据量的时候要慎用。少量没问题。
     *
     * @param pattern 模糊匹配
     * @return 键值
     */
    public static Flux<String> keys(String pattern) {
        return reactiveRedisTemplate.keys(RedisUtil.keyTrans.apply(pattern));
    }

    public static Flux<String> scan(String pattern) {
        return scan(pattern, scan_count, DataType.NONE);
    }

    public static Flux<String> scan(String pattern, Long count) {
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
    public static Flux<String> scan(String pattern, Long count, DataType type) {
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
    public static Flux<String> scan(ScanOptions options) {
        if (null != options.getCount() && options.getCount() <= 0) return Flux.empty();
        return reactiveRedisTemplate.scan(RedisUtil.copyScanOptions(options, RedisUtil.keyTrans));
    }

    /**
     * 删除key
     *
     * @param keys 键数组
     * @return 删除个数
     */
    public static Mono<Long> delete(String... keys) {
        return reactiveRedisTemplate.delete(keys);
    }

    /**
     * 删除key
     *
     * @param publisher key的提供者
     * @return 删除个数
     */
    public static Mono<Long> delete(Publisher<String> publisher) {
        return reactiveRedisTemplate.delete(publisher);
    }

    public static Mono<Boolean> expire(String key, long time) {
        return reactiveRedisTemplate.expire(key, Duration.of(time, chrono_unit));
    }

    /**
     * 修改key的过期时间
     *
     * @param key  键
     * @param time 过期时间
     * @param unit 时间单位
     * @return 是否修改成功
     */
    public static Mono<Boolean> expire(String key, long time, ChronoUnit unit) {
        return reactiveRedisTemplate.expire(key, Duration.of(time, unit));
    }

    //  ------------------------------------------- value -----------------------------------------------------

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     * @param <V>   泛型
     * @return 是否写入成功
     */
    public static <V> Mono<Boolean> set(String key, V value) {
        return valueOp().set(RedisUtil.keyTrans.apply(key), value);
    }

    /**
     * 插入缓存默认时间
     *
     * @param key   键
     * @param value 值
     * @param <V>   泛型
     * @return 是否写入成功
     */
    public static <V> Mono<Boolean> setDefaultExpire(String key, V value) {
        return set(key, value, Duration.of(timeout, chrono_unit));
    }

    public static <V> Mono<Boolean> set(String key, V value, long expire) {
        return set(key, value, Duration.of(expire, chrono_unit));
    }

    /**
     * 写入缓存设置过期时间
     *
     * @param key      键
     * @param value    值
     * @param duration 过期时间
     * @param <V>      泛型
     * @return 是否写入成功
     */
    public static <V> Mono<Boolean> set(String key, V value, Duration duration) {
        return valueOp().set(RedisUtil.keyTrans.apply(key), value, duration);
    }

    /**
     * 获取key的值
     *
     * @param key 键
     * @param <V> 泛型
     * @return 值
     */
    public static <V> Mono<V> get(String key) {
        ReactiveValueOperations<String, V> valueOp = valueOp();
        return valueOp.get(RedisUtil.keyTrans.apply(key));
    }

    //  ------------------------------------------- hash -----------------------------------------------------

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static Mono<Boolean> hPut(String key, String item, Object value) {
        return hashOp().put(RedisUtil.keyTrans.apply(key), item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key 键
     * @param map 值
     * @return true 成功 false失败
     */
    public static <V> Mono<Boolean> hPutAll(String key, Map<String, V> map) {
        return hashOp().putAll(RedisUtil.keyTrans.apply(key), map);
    }

    /**
     * 获取hash值
     *
     * @param key     键
     * @param hashKey 项
     * @return 值
     */
    public static <V> Mono<V> hGet(String key, String hashKey) {
        ReactiveHashOperations<String, String, V> hashOp = hashOp();
        return hashOp.get(RedisUtil.keyTrans.apply(key), hashKey);
    }

    /**
     * 删除hash表中的值
     *
     * @param key      键
     * @param hashKeys hashKey集合
     * @return 删除个数
     */
    public static Mono<Long> hRemove(String key, String... hashKeys) {
        return hashOp().remove(RedisUtil.keyTrans.apply(key), (Object) hashKeys);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key     键 不能为null
     * @param hashKey 项 不能为null
     * @return true 存在 false不存在
     */
    public static Mono<Boolean> hHasKey(String key, String hashKey) {
        return hashOp().hasKey(RedisUtil.keyTrans.apply(key), hashKey);
    }

    @Autowired
    public void setReactiveRedisTemplate(ReactiveRedisTemplate reactiveRedisTemplate) {
        ReactiveRedisUtil.reactiveRedisTemplate = reactiveRedisTemplate;
    }

}
