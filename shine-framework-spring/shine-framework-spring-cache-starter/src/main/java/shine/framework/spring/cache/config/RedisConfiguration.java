package shine.framework.spring.cache.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import shine.framework.spring.cache.util.RedisCompoundUtil;
import shine.framework.spring.cache.util.RedisConfigUtil;
import shine.framework.spring.cache.util.RedisUtil;

/**
 * @author SilenceShine
 * @since 1.0
 */
@AutoConfiguration(before = RedisAutoConfiguration.class)
@ConditionalOnClass({RedisConnectionFactory.class, RedisTemplate.class})
public class RedisConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public <V> RedisTemplate<String, V> redisTemplate(RedisConnectionFactory connectionFactory) {
        return RedisConfigUtil.buildStringJsonTemplate(connectionFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        return RedisConfigUtil.buildStringStringTemplate(connectionFactory);
    }

    @Bean
    @ConditionalOnBean({RedisTemplate.class})
    @ConditionalOnMissingBean(name = "redisUtil")
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }

    @Bean
    @ConditionalOnBean({RedisUtil.class})
    @ConditionalOnMissingBean(name = "redisCompoundUtil")
    public RedisCompoundUtil redisCompoundUtil() {
        return new RedisCompoundUtil();
    }

}