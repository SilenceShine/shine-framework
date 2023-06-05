package io.github.SilenceShine.shine.spring.exception;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.stream.Collectors;

/**
 * webflux异常处理配置
 *
 * @author SilenceShine
 * @since 1.0
 */
@AutoConfiguration(before = ErrorWebFluxAutoConfiguration.class)
@ConditionalOnClass(WebFluxConfigurer.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties({ServerProperties.class, WebProperties.class})
public class WebfluxErrorConfiguration {

    @Bean
    @Order(-2)
    @ConditionalOnMissingBean(value = ErrorWebExceptionHandler.class, search = SearchStrategy.CURRENT)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes attributes, WebProperties properties,
                                                             ObjectProvider<ViewResolver> resolvers,
                                                             ServerCodecConfigurer configurer, ApplicationContext context) {
        WebfluxErrorWebExceptionHandler handler = new WebfluxErrorWebExceptionHandler(attributes, properties.getResources(), context);
        handler.setViewResolvers(resolvers.orderedStream().collect(Collectors.toList()));
        handler.setMessageWriters(configurer.getWriters());
        handler.setMessageReaders(configurer.getReaders());
        return handler;
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
    public WebfluxErrorAttributes errorAttributes() {
        return new WebfluxErrorAttributes();
    }

}
