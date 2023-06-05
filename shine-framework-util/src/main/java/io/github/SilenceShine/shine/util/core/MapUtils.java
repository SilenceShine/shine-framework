package io.github.SilenceShine.shine.util.core;

import cn.hutool.core.map.MapUtil;

import java.util.Map;
import java.util.function.Consumer;

/**
 * map工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
public class MapUtils extends MapUtil {

    public static <K, V> void acceptIfPresent(Map<K, V> map, K key, Consumer<V> consumer) {
        V value = map.get(key);
        if (null != value) {
            consumer.accept(value);
        }
    }

}
