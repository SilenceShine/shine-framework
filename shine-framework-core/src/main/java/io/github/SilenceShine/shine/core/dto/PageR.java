package io.github.SilenceShine.shine.core.dto;

import io.github.SilenceShine.shine.core.dto.data.PageData;
import io.github.SilenceShine.shine.core.exception.ResultStatus;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import io.github.SilenceShine.shine.core.exception.ExceptionEnum;

import java.beans.Transient;
import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

/**
 * 分页结果
 *
 * @author SilenceShine
 * @since 1.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public non-sealed class PageR<E> extends R<PageData<E>> {

    @Serial
    private static final long serialVersionUID = -805166746773905091L;

    @Transient
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Transient
    public boolean isNotEmpty() {
        return data.isNotEmpty();
    }

    public static <E> PageR<E> ok() {
        return ok(null);
    }

    public static <E> PageR<E> ok(long page, long size) {
        return ok(0L, page, size, Collections.emptyList());
    }

    public static <E> PageR<E> ok(long total, long page, long size, Collection<E> data) {
        return ok(PageData.of(total, page, size, data));
    }

    public static <E> PageR<E> ok(PageData<E> pageData) {
        return PageR.<E>builder()
                .code(ResultStatus.OK)
                .success(true)
                .data(pageData)
                .build();
    }

    public static <E> PageR<E> error(String message) {
        return error(ResultStatus.SERVER_ERROR, message);
    }

    public static <E> PageR<E> error(ExceptionEnum anEnum) {
        return error(anEnum.code(), anEnum.message());
    }

    public static <E> PageR<E> error(int code, String message) {
        return PageR.<E>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

}

