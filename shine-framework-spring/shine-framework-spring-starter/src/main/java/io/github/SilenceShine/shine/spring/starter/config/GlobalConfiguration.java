package io.github.SilenceShine.shine.spring.starter.config;

import io.github.SilenceShine.shine.core.util.ValidatorUtil;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;

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

}
