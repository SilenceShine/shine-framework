package io.github.SilenceShine.shine.spring.cache.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.SilenceShine.shine.util.core.Builder;
import io.github.SilenceShine.shine.util.json.module.ShineModule;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置构造工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
public class RedisConfigUtil {

    /**
     * 获取 {@link RedisTemplate <String:V>} 的 template
     *
     * @param connectionFactory 连接工厂
     * @return {@link RedisTemplate<String:V>}
     */
    public static <V> RedisTemplate<String, V> buildStringJsonTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisSerializer keySerializer = StringRedisSerializer.UTF_8;
        Jackson2JsonRedisSerializer<Object> valueSerializer = buildJackson2JsonRedisSerializer();
        return Builder.of(RedisTemplate<String, V>::new)
            .with(RedisTemplate::setConnectionFactory, connectionFactory)
            .with(RedisTemplate::setKeySerializer, keySerializer)
            .with(RedisTemplate::setValueSerializer, valueSerializer)
            .with(RedisTemplate::setHashKeySerializer, keySerializer)
            .with(RedisTemplate::setHashValueSerializer, valueSerializer)
            .with(RedisTemplate::afterPropertiesSet)
            .build();
    }

    /**
     * 获取 {@link StringRedisTemplate} 的 template
     *
     * @param connectionFactory 连接工厂
     * @return {@link StringRedisTemplate}
     */
    public static StringRedisTemplate buildStringStringTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    /**
     * 获取 {@link StringRedisTemplate} 的 template
     *
     * @param connectionFactory 连接工厂
     * @return {@link StringRedisTemplate}
     */
    public static StringRedisTemplate buildStringBytesTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisSerializer keySerializer = StringRedisSerializer.UTF_8;
        JdkSerializationRedisSerializer valueSerializer = new JdkSerializationRedisSerializer();
        return Builder.of(StringRedisTemplate::new)
            .with(RedisTemplate::setConnectionFactory, connectionFactory)
            .with(RedisTemplate::setKeySerializer, keySerializer)
            .with(RedisTemplate::setValueSerializer, valueSerializer)
            .with(RedisTemplate::setHashKeySerializer, keySerializer)
            .with(RedisTemplate::setHashValueSerializer, valueSerializer)
            .with(RedisTemplate::afterPropertiesSet)
            .build();
    }

    /**
     * 获取一个key为string value为T的 RedisTemplate
     *
     * @param <V>                  the type parameter
     * @param connectionFactory    连接工厂
     * @param valueRedisSerializer value序列化serializer
     * @return {@link RedisTemplate<String:V>}
     */
    public static <V> RedisTemplate<String, V> buildStringTemplate(RedisConnectionFactory connectionFactory,
                                                                   RedisSerializer<V> valueRedisSerializer) {
        StringRedisSerializer keySerializer = StringRedisSerializer.UTF_8;
        return Builder.of(RedisTemplate<String, V>::new)
            .with(RedisTemplate::setConnectionFactory, connectionFactory)
            .with(RedisTemplate::setKeySerializer, keySerializer)
            .with(RedisTemplate::setValueSerializer, valueRedisSerializer)
            .with(RedisTemplate::setHashKeySerializer, keySerializer)
            .with(RedisTemplate::setHashValueSerializer, valueRedisSerializer)
            .with(RedisTemplate::afterPropertiesSet)
            .build();
    }

    /**
     * 构建json serializer
     */
    public static Jackson2JsonRedisSerializer<Object> buildJackson2JsonRedisSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(ShineModule.INSTANCE);
        return new Jackson2JsonRedisSerializer<>(mapper, Object.class);
    }

}
