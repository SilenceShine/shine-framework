package io.github.SilenceShine.shine.spring.orm.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.github.SilenceShine.shine.orm.mybatis.handler.AbstractMetaObjectHandler;
import io.github.SilenceShine.shine.orm.mybatis.handler.MultipleMetaObjectHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Configuration
@ConditionalOnBean(MybatisPlusAutoConfiguration.class)
@ConditionalOnClass(MetaObjectHandler.class)
public class MybatisPlusConfiguration {

    @Bean
    @Primary
    @SuppressWarnings({"rawtypes"})
    @ConditionalOnMissingBean
    public MultipleMetaObjectHandler multipleMetaObjectHandler(List<AbstractMetaObjectHandler> handlers) {
        return new MultipleMetaObjectHandler(handlers);
    }

}
