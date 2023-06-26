package io.github.SilenceShine.shine.core.util;

import io.github.SilenceShine.shine.core.exception.BizException;
import io.github.SilenceShine.shine.core.exception.ValidatorException;
import io.github.SilenceShine.shine.util.json.JacksonUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import static io.github.SilenceShine.shine.core.exception.ValidatorException.VALIDATOR_INIT;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Slf4j
public class ValidatorUtil {

    private static Validator validator;

    public static <T> void validate(T object, Function<ConstraintViolation<T>, String> function, Class<?>... groups) {
        check();
        validator.validate(object, groups)
            .stream()
            .findAny()
            .ifPresent(violation -> {
                throw new BizException(ValidatorException.VALIDATOR, function.apply(violation));
            });
    }

    /**
     * 校验string是否满足某个类型
     */
    public static <T> void validatedStringClass(String str, Class<T> clazz) {
        check();
        T obj = JacksonUtil.parseJson(str, clazz);
        validate(obj, ConstraintViolation::getMessage);
    }

    public static void check() {
        AssertsUtil.isTrue(validator != null, VALIDATOR_INIT);
    }

    public static void init(Validator validator) {
        ValidatorUtil.validator = validator;
    }

}
