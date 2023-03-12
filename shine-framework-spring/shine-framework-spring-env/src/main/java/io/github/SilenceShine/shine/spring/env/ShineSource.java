package io.github.SilenceShine.shine.spring.env;

import cn.hutool.core.util.IdUtil;
import cn.hutool.system.oshi.OshiUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class ShineSource {

    private static final Map<String, Supplier<Object>> MAP = new HashMap<>();

    static {
        MAP.put("timestamp", LocalDateTime::now);
        MAP.put("uuid.fast", IdUtil::fastUUID);
        MAP.put("uuid.simple", IdUtil::simpleUUID);
        MAP.put("uuid.random", IdUtil::randomUUID);
        MAP.put("os.family", () -> OshiUtil.getOs().getFamily());
        MAP.put("os.version", () -> OshiUtil.getOs().getVersionInfo().toString());
    }

    public static Object get(String key) {
        return MAP.get(key).get();
    }

    public static Object put(String key, Supplier<Object> supplier) {
        return MAP.putIfAbsent(key, supplier);
    }

}
