package io.github.SilenceShine.shine.core.dto.reactive;

import io.github.SilenceShine.shine.core.dto.data.PageData;
import reactor.core.publisher.Mono;
import io.github.SilenceShine.shine.core.dto.MultiR;
import io.github.SilenceShine.shine.core.dto.PageR;
import io.github.SilenceShine.shine.core.dto.SingleR;
import io.github.SilenceShine.shine.core.exception.ExceptionEnum;

import java.util.Collection;

/**
 * reactor 响应式返回
 *
 * @author SilenceShine
 * @since 1.0
 */
public class ReactorR {

    // ---------------------------------- single --------------------------------------

    public static <E> Mono<SingleR<E>> single() {
        return Mono.just(SingleR.ok());
    }

    public static <E> Mono<SingleR<E>> single(E element) {
        return Mono.just(SingleR.ok(element));
    }

    public static <E> Mono<SingleR<E>> singleError(String message) {
        return Mono.just(SingleR.error(message));
    }

    public static <E> Mono<SingleR<E>> singleError(ExceptionEnum anEnum) {
        return Mono.just(SingleR.error(anEnum));
    }

    public static <E> Mono<SingleR<E>> singleError(int code, String message) {
        return Mono.just(SingleR.error(code, message));
    }

    // ---------------------------------- multi --------------------------------------

    public static <E> Mono<MultiR<E>> multi() {
        return Mono.just(MultiR.ok());
    }

    @SafeVarargs
    public static <E> Mono<MultiR<E>> multi(E... elements) {
        return Mono.just(MultiR.ok(elements));
    }

    public static <E> Mono<MultiR<E>> multi(Collection<E> elements) {
        return Mono.just(MultiR.ok(elements));
    }

    public static <E> Mono<MultiR<E>> multiError(String message) {
        return Mono.just(MultiR.error(message));
    }

    public static <E> Mono<MultiR<E>> multiError(ExceptionEnum anEnum) {
        return Mono.just(MultiR.error(anEnum));
    }

    public static <E> Mono<MultiR<E>> multiError(int code, String message) {
        return Mono.just(MultiR.error(code, message));
    }

    // ---------------------------------- page --------------------------------------

    public static <E> Mono<PageR<E>> page() {
        return Mono.just(PageR.ok());
    }

    public static <E> Mono<PageR<E>> page(int page, int size) {
        return Mono.just(PageR.ok(page, size));
    }

    public static <E> Mono<PageR<E>> page(PageData<E> pageData) {
        return Mono.just(PageR.ok(pageData));
    }

    public static <E> Mono<PageR<E>> page(long total, int page, int size, Collection<E> elements) {
        return Mono.just(PageR.ok(total, page, size, elements));
    }

    public static <E> Mono<PageR<E>> pageError(String message) {
        return Mono.just(PageR.error(message));
    }

    public static <E> Mono<PageR<E>> pageError(ExceptionEnum anEnum) {
        return Mono.just(PageR.error(anEnum));
    }

    public static <E> Mono<PageR<E>> pageError(int code, String message) {
        return Mono.just(PageR.error(code, message));
    }

}