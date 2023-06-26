package io.github.SilenceShine.shine.core.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public enum ValidatorException implements ExceptionEnum {

    VALIDATOR_INIT(ResultStatus.BAD_REQUEST, "validator 未初始化!"),
    VALIDATOR(ResultStatus.BAD_REQUEST, "validator 异常,报错信息:%s !"),
    ;

    int code;
    String message;

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
