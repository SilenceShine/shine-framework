package shine.framework.spring.env;

import org.apache.commons.logging.Log;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.log.LogMessage;

import java.util.Random;

/**
 * 为啥必须用random 因为spring 写死了{@link org.springframework.boot.context.properties.source.SpringConfigurationPropertySource#containsDescendantOf(ConfigurationPropertyName)}
 *
 * @author SilenceShine
 * @since 1.0
 */
public class ShinePropertySource extends PropertySource<Random> {

    public static final String SHINE_PROPERTY_SOURCE_NAME = "shine";
    private static final String PREFIX = SHINE_PROPERTY_SOURCE_NAME + ".";

    public ShinePropertySource(String name, Random source) {
        super(name, source);
    }

    public ShinePropertySource(String name) {
        super(name);
    }

    @Override
    public Object getProperty(String name) {
        if (!name.startsWith(PREFIX)) {
            return null;
        }
        logger.trace(LogMessage.format("Generating dependent property for '%s'", name));
        return ShineSource.get(name.substring(PREFIX.length()));
    }

    static void addToEnvironment(ConfigurableEnvironment environment, Log logger) {
        MutablePropertySources sources = environment.getPropertySources();
        PropertySource<?> existing = sources.get(SHINE_PROPERTY_SOURCE_NAME);
        if (existing != null) {
            logger.trace("DependentPropertySource already present");
            return;
        }
        ShinePropertySource dependentSource = new ShinePropertySource(SHINE_PROPERTY_SOURCE_NAME);
        if (sources.get(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME) != null) {
            sources.addAfter(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, dependentSource);
        } else {
            sources.addLast(dependentSource);
        }
        logger.trace("DependentPropertySource add to Environment");
    }

}

