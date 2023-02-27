package shine.framework.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import shine.framework.util.json.module.ShineModule;
import shine.framework.util.log.LogUtil;

/**
 * jackson 工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
public class JacksonUtils {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ShineModule());
    }

    /**
     * 增加自定义,如果需要覆盖,那么得先覆盖在调用
     */
    public static void addModule(SimpleModule module) {
        objectMapper.registerModule(module);
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LogUtil.error(JacksonUtils.class, "jackson error:{}", e.getMessage());
        }
        return null;
    }

    public static <T> T parseJson(String jsonValue, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonValue, valueType);
        } catch (JsonProcessingException e) {
            LogUtil.error(JacksonUtils.class, "jackson error:{}", e.getMessage());
        }
        return null;
    }

    public static <T> T parseJson(String jsonValue, JavaType valueType) {
        try {
            return objectMapper.readValue(jsonValue, valueType);
        } catch (JsonProcessingException e) {
            LogUtil.error(JacksonUtils.class, "jackson error:{}", e.getMessage());
        }
        return null;
    }

    public static <T> T parseJson(String jsonValue, TypeReference<T> reference) {
        try {
            return objectMapper.readValue(jsonValue, reference);
        } catch (JsonProcessingException e) {
            LogUtil.error(JacksonUtils.class, "jackson error:{}", e.getMessage());
        }
        return null;
    }

}
