package shine.framework.spring.jackson.config;


import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import shine.framework.spring.jackson.properties.JacksonProperties;

/**
 * @author SilenceShine
 * @since 1.0
 */
@AutoConfiguration
@AllArgsConstructor
@EnableConfigurationProperties(JacksonProperties.class)
public class Jackson2AutoConfiguration {


}
