package shine.framework.core.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import shine.framework.core.exception.ExceptionEnum;
import shine.framework.core.exception.ResultStatus;

import java.io.Serial;

/**
 * 单条记录
 *
 * @author SilenceShine
 * @since 1.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public non-sealed class SingleR<E> extends R<E> {

    @Serial
    private static final long serialVersionUID = -6837474604451326113L;

    public static <E> SingleR<E> ok() {
        return ok(null);
    }

    public static <E> SingleR<E> ok(E data) {
        return SingleR.<E>builder()
                .success(true)
                .code(ResultStatus.OK)
                .data(data)
                .build();
    }

    public static <E> SingleR<E> error(String message) {
        return error(ResultStatus.SERVER_ERROR, message);
    }

    public static <E> SingleR<E> error(ExceptionEnum anEnum) {
        return error(anEnum.code(), anEnum.message());
    }

    public static <E> SingleR<E> error(int code, String message) {
        return SingleR.<E>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

}
