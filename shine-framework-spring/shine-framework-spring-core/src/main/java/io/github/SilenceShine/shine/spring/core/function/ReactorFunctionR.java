package io.github.SilenceShine.shine.spring.core.function;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import io.github.SilenceShine.shine.core.dto.MultiR;
import io.github.SilenceShine.shine.core.dto.PageR;
import io.github.SilenceShine.shine.core.dto.R;
import io.github.SilenceShine.shine.core.dto.SingleR;
import io.github.SilenceShine.shine.core.dto.data.PageData;
import io.github.SilenceShine.shine.core.exception.ExceptionEnum;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * reactor函数式返回
 *
 * @author SilenceShine
 * @since 1.0
 */
public class ReactorFunctionR {

    // ---------------------------------- single --------------------------------------

    public static Mono<ServerResponse> single() {
        return response(SingleR::ok);
    }

    public static <D> Mono<ServerResponse> single(D data) {
        return response(() -> SingleR.ok(data));
    }

    public static Mono<ServerResponse> singleError(String message) {
        return response(() -> SingleR.error(message));
    }

    public static Mono<ServerResponse> singleError(ExceptionEnum anEnum) {
        return response(() -> SingleR.error(anEnum));
    }

    public static Mono<ServerResponse> singleError(int code, String message) {
        return response(() -> SingleR.error(code, message));
    }

    // ---------------------------------- multi ---------------------------------------

    public static Mono<ServerResponse> multi() {
        return response(MultiR::ok);
    }

    @SafeVarargs
    public static <D> Mono<ServerResponse> multi(D... data) {
        return response(() -> MultiR.ok(data));
    }

    public static <D> Mono<ServerResponse> multi(Collection<D> data) {
        return response(() -> MultiR.ok(data));
    }

    public static Mono<ServerResponse> multiError(String message) {
        return response(() -> MultiR.error(message));
    }

    public static Mono<ServerResponse> multiError(ExceptionEnum anEnum) {
        return response(() -> MultiR.error(anEnum));
    }

    public static Mono<ServerResponse> multiError(int code, String message) {
        return response(() -> MultiR.error(code, message));
    }

    // ---------------------------------- page ---------------------------------------

    public static Mono<ServerResponse> page() {
        return response(PageR::ok);
    }

    public static Mono<ServerResponse> page(int page, int size) {
        return response(() -> PageR.ok(page, size));
    }

    public static <D> Mono<ServerResponse> page(PageData<D> pageData) {
        return response(() -> PageR.ok(pageData));
    }

    public static <D> Mono<ServerResponse> page(long total, int page, int size, Collection<D> data) {
        return response(() -> PageR.ok(total, page, size, data));
    }

    public static Mono<ServerResponse> pageError(String message) {
        return response(() -> PageR.error(message));
    }

    public static Mono<ServerResponse> pageError(ExceptionEnum anEnum) {
        return response(() -> PageR.error(anEnum));
    }

    public static Mono<ServerResponse> pageError(int code, String message) {
        return response(() -> PageR.error(code, message));
    }

    /**
     * 构建返回response
     *
     * @param rSupplier 消息提供
     * @return 响应结果
     */
    public static Mono<ServerResponse> response(Supplier<R<?>> rSupplier) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(rSupplier.get());
    }

}

