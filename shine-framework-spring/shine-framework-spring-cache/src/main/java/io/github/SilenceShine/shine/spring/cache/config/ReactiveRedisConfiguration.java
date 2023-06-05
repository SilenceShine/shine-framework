package io.github.SilenceShine.shine.spring.cache.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import reactor.core.publisher.Mono;
import io.github.SilenceShine.shine.spring.cache.util.ReactiveRedisConfigUtil;
import io.github.SilenceShine.shine.spring.cache.util.ReactiveRedisUtil;

/**
 * 响应式redis配置
 *
 * @author SilenceShine
 * @since 1.0
 */
@AutoConfiguration(before = RedisReactiveAutoConfiguration.class, after = RedisAutoConfiguration.class)
@ConditionalOnClass({ReactiveRedisConnectionFactory.class, ReactiveRedisTemplate.class, Mono.class})
public class ReactiveRedisConfiguration {

    @Bean(name = "reactiveRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    @ConditionalOnMissingBean(name = "reactiveRedisTemplate")
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        return ReactiveRedisConfigUtil.buildStringJsonTemplate(connectionFactory);
    }

    @Bean(name = "reactiveStringRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    @ConditionalOnMissingBean(name = "reactiveStringRedisTemplate")
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        return ReactiveRedisConfigUtil.buildStringStringTemplate(connectionFactory);
    }

    @Bean
    @ConditionalOnBean({ReactiveRedisTemplate.class, ReactiveStringRedisTemplate.class})
    @ConditionalOnMissingBean(name = "reactiveRedisUtils")
    public ReactiveRedisUtil reactiveRedisUtils() {
        return new ReactiveRedisUtil();
    }

}
