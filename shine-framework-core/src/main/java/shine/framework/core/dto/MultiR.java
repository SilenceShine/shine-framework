package shine.framework.core.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import shine.framework.core.exception.ExceptionEnum;
import shine.framework.core.exception.ResultStatus;

import java.beans.Transient;
import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 集合返回
 *
 * @author SilenceShine
 * @since 1.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public non-sealed class MultiR<E> extends R<Collection<E>> {

    @Serial
    private static final long serialVersionUID = 4982957146196864193L;

    @Transient
    public boolean isEmpty() {
        return null == data || data.size() == 0;
    }

    @Transient
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static <E> MultiR<E> ok() {
        return ok(Collections.emptyList());
    }

    @SafeVarargs
    public static <E> MultiR<E> ok(E... data) {
        return ok(Arrays.asList(data));
    }

    public static <E> MultiR<E> ok(Collection<E> data) {
        return MultiR.<E>builder()
                .success(true)
                .code(ResultStatus.OK)
                .data(data)
                .build();
    }

    public static <E> MultiR<E> error(String message) {
        return error(ResultStatus.SERVER_ERROR, message);
    }

    public static <E> MultiR<E> error(ExceptionEnum anEnum) {
        return error(anEnum.code(), anEnum.message());
    }

    public static <E> MultiR<E> error(int code, String message) {
        return MultiR.<E>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

}
