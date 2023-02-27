package shine.framework.core.dto.reactive;

import io.smallrye.mutiny.Uni;
import shine.framework.core.dto.MultiR;
import shine.framework.core.dto.PageR;
import shine.framework.core.dto.SingleR;
import shine.framework.core.dto.data.PageData;
import shine.framework.core.exception.ExceptionEnum;

import java.util.Collection;

/**
 * mutiny 响应式返回
 *
 * @author SilenceShine
 * @since 1.0
 */
public class MutinyR {

    // ---------------------------------- single --------------------------------------

    public static <E> Uni<SingleR<E>> single() {
        return Uni.createFrom().item(SingleR.ok());
    }

    public static <E> Uni<SingleR<E>> single(E element) {
        return Uni.createFrom().item(SingleR.ok(element));
    }

    public static <E> Uni<SingleR<E>> singleError(String message) {
        return Uni.createFrom().item(SingleR.error(message));
    }

    public static <E> Uni<SingleR<E>> singleError(ExceptionEnum anEnum) {
        return Uni.createFrom().item(SingleR.error(anEnum));
    }

    public static <E> Uni<SingleR<E>> singleError(int code, String message) {
        return Uni.createFrom().item(SingleR.error(code, message));
    }

    // ---------------------------------- multi --------------------------------------

    public static <E> Uni<MultiR<E>> multi() {
        return Uni.createFrom().item(MultiR.ok());
    }

    @SafeVarargs
    public static <E> Uni<MultiR<E>> multi(E... elements) {
        return Uni.createFrom().item(MultiR.ok(elements));
    }

    public static <E> Uni<MultiR<E>> multi(Collection<E> elements) {
        return Uni.createFrom().item(MultiR.ok(elements));
    }

    public static <E> Uni<MultiR<E>> multiError(String message) {
        return Uni.createFrom().item(MultiR.error(message));
    }

    public static <E> Uni<MultiR<E>> multiError(ExceptionEnum anEnum) {
        return Uni.createFrom().item(MultiR.error(anEnum));
    }

    public static <E> Uni<MultiR<E>> multiError(int code, String message) {
        return Uni.createFrom().item(MultiR.error(code, message));
    }

    // ---------------------------------- page --------------------------------------

    public static <E> Uni<PageR<E>> page() {
        return Uni.createFrom().item(PageR.ok());
    }

    public static <E> Uni<PageR<E>> page(int page, int size) {
        return Uni.createFrom().item(PageR.ok(page, size));
    }

    public static <E> Uni<PageR<E>> page(PageData<E> pageData) {
        return Uni.createFrom().item(PageR.ok(pageData));
    }

    public static <E> Uni<PageR<E>> page(long total, int page, int size, Collection<E> elements) {
        return Uni.createFrom().item(PageR.ok(total, page, size, elements));
    }

    public static <E> Uni<PageR<E>> pageError(String message) {
        return Uni.createFrom().item(PageR.error(message));
    }

    public static <E> Uni<PageR<E>> pageError(ExceptionEnum anEnum) {
        return Uni.createFrom().item(PageR.error(anEnum));
    }

    public static <E> Uni<PageR<E>> pageError(int code, String message) {
        return Uni.createFrom().item(PageR.error(code, message));
    }

}
