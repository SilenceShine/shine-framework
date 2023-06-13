package io.github.SilenceShine.shine.spring.env;

import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
public class ShinePropertySourceEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    public static final int ORDER = RandomValuePropertySourceEnvironmentPostProcessor.ORDER;

    private final Log logger;

    public ShinePropertySourceEnvironmentPostProcessor(DeferredLogFactory logFactory) {
        this.logger = logFactory.getLog(ShinePropertySourceEnvironmentPostProcessor.class);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ShinePropertySource.addToEnvironment(environment, this.logger);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }

}
