package io.github.SilenceShine.shine.util.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SilenceShine
 * @since 1.0
 */
class MapUtilsTest {

    public static void main(String[] args) {
        String key = "1";
        Map<String, String> map = new HashMap<>();
        map.put(key, "value");
        MapUtils.acceptIfPresent(map, "1", System.out::println);
    }

}