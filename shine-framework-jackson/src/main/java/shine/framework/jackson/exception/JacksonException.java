package shine.framework.jackson.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shine.framework.core.exception.ExceptionEnum;
import shine.framework.core.exception.ResultStatus;

/**
 * Jackson 异常类
 *
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum JacksonException implements ExceptionEnum {
    BIG_DECIMAL_DESERIALIZER(ResultStatus.BAD_REQUEST, "BigDecimal 反序列化异常,值:%s !"),
    LONG_DESERIALIZER(ResultStatus.BAD_REQUEST, "Long 反序列化异常,值:%s !"),
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