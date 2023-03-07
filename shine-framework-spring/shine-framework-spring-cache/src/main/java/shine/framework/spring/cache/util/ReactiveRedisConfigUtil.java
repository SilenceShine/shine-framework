package shine.framework.spring.cache.util;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 响应式redis配置构造工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
public class ReactiveRedisConfigUtil {

    /**
     * 获取 {@link ReactiveStringRedisTemplate} 的 template
     *
     * @param connectionFactory 连接工厂
     * @return {@link ReactiveStringRedisTemplate}
     */
    public static ReactiveStringRedisTemplate buildStringStringTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveStringRedisTemplate(connectionFactory);
    }

    /**
     * 获取 {@link ReactiveRedisTemplate <String:Object>} 的 template
     *
     * @param connectionFactory 连接工厂
     * @return {@link ReactiveRedisTemplate<String:Object>}
     */
    public static ReactiveRedisTemplate<String, Object> buildStringJsonTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        StringRedisSerializer keySerializer = StringRedisSerializer.UTF_8;
        Jackson2JsonRedisSerializer<Object> valueSerializer = RedisConfigUtil.buildJackson2JsonRedisSerializer();
        return new ReactiveRedisTemplate<>(connectionFactory, buildRedisSerializationContext(keySerializer, valueSerializer));
    }

    /**
     * 构建 RedisSerializationContext
     */
    public static <K, V> RedisSerializationContext<K, V> buildRedisSerializationContext(RedisSerializer<K> keySerializer,
                                                                                        RedisSerializer<V> valueSerializer) {
        return RedisSerializationContext.<K, V>newSerializationContext()
                .key(keySerializer)
                .value(valueSerializer)
                .hashKey(keySerializer)
                .hashValue(valueSerializer)
                .build();
    }
}
