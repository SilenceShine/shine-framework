package io.github.SilenceShine.shine.template;

import lombok.AllArgsConstructor;
import io.github.SilenceShine.shine.core.exception.ExceptionEnum;
import io.github.SilenceShine.shine.core.exception.ResultStatus;

/**
 * @author SilenceShine
 * @since 1.0
 */
@AllArgsConstructor
public enum TemplateException implements ExceptionEnum {

    TEMPLATE_TYPE(ResultStatus.BAD_REQUEST, "模板type异常!"),
    ;

    private final int code;
    private final String message;

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}

