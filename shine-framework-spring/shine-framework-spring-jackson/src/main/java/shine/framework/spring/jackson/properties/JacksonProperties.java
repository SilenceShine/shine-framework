package shine.framework.spring.jackson.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import shine.framework.constant.ConfigConstant;

import java.util.List;

/**
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
@ConfigurationProperties(ConfigConstant.PARENT + "jackson")
public class JacksonProperties {

    /**
     * 转换配置
     */
    private List<String> converts = List.of();

}
