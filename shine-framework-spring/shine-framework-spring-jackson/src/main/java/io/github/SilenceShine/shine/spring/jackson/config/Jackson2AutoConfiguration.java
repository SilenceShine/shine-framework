package io.github.SilenceShine.shine.spring.jackson.config;


import io.github.SilenceShine.shine.spring.jackson.properties.JacksonProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author SilenceShine
 * @since 1.0
 */
@AutoConfiguration
@AllArgsConstructor
@EnableConfigurationProperties(JacksonProperties.class)
public class Jackson2AutoConfiguration {


}
