package io.github.SilenceShine.shine.spring.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "shine")
public class ShineProperties {


}
