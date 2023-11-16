package io.github.SilenceShine.shine.spring.starter.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.SilenceShine.shine.constant.DateConstant;
import io.github.SilenceShine.shine.core.util.ValidatorUtil;
import io.github.SilenceShine.shine.util.json.module.ShineModule;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@AutoConfiguration
@AllArgsConstructor
public class GlobalConfiguration {

    private final Validator validator;

    @PostConstruct
    public void init() {
        ValidatorUtil.init(validator);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.modules(new ShineModule());
            // 地区
            builder.timeZone(DateConstant.TIME_ZONE);
            // 序列化枚举
            builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            // 空属性过滤
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        };
    }

}
