package io.github.SilenceShine.shine.spring.core.function;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.ServerResponse;
import io.github.SilenceShine.shine.core.dto.MultiR;
import io.github.SilenceShine.shine.core.dto.PageR;
import io.github.SilenceShine.shine.core.dto.R;
import io.github.SilenceShine.shine.core.dto.SingleR;
import io.github.SilenceShine.shine.core.dto.data.PageData;
import io.github.SilenceShine.shine.core.exception.ExceptionEnum;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 函数式返回
 *
 * @author SilenceShine
 * @since 1.0
 */
public class FunctionR {

    // ---------------------------------- single --------------------------------------

    public static ServerResponse single() {
        return response(SingleR::ok);
    }

    public static <D> ServerResponse single(D data) {
        return response(() -> SingleR.ok(data));
    }

    public static ServerResponse singleError(String message) {
        return response(() -> SingleR.error(message));
    }

    public static ServerResponse singleError(ExceptionEnum anEnum) {
        return response(() -> SingleR.error(anEnum));
    }

    public static ServerResponse singleError(int code, String message) {
        return response(() -> SingleR.error(code, message));
    }

    // ---------------------------------- multi ---------------------------------------

    public static ServerResponse multi() {
        return response(MultiR::ok);
    }

    @SafeVarargs
    public static <D> ServerResponse multi(D... data) {
        return response(() -> MultiR.ok(data));
    }

    public static <D> ServerResponse multi(Collection<D> data) {
        return response(() -> MultiR.ok(data));
    }

    public static ServerResponse multiError(String message) {
        return response(() -> MultiR.error(message));
    }

    public static ServerResponse multiError(ExceptionEnum anEnum) {
        return response(() -> MultiR.error(anEnum));
    }

    public static ServerResponse multiError(int code, String message) {
        return response(() -> MultiR.error(code, message));
    }

    // ---------------------------------- page ---------------------------------------

    public static ServerResponse page() {
        return response(PageR::ok);
    }

    public static ServerResponse page(int page, int size) {
        return response(() -> PageR.ok(page, size));
    }

    public static <D> ServerResponse page(PageData<D> pageData) {
        return response(() -> PageR.ok(pageData));
    }

    public static <D> ServerResponse page(long total, int page, int size, Collection<D> data) {
        return response(() -> PageR.ok(total, page, size, data));
    }

    public static ServerResponse pageError(String message) {
        return response(() -> PageR.error(message));
    }

    public static ServerResponse pageError(ExceptionEnum anEnum) {
        return response(() -> PageR.error(anEnum));
    }

    public static ServerResponse pageError(int code, String message) {
        return response(() -> PageR.error(code, message));
    }

    /**
     * 构建返回response
     *
     * @param rSupplier 消息提供
     * @return 响应结果
     */
    public static ServerResponse response(Supplier<R<?>> rSupplier) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(rSupplier.get());
    }

}
