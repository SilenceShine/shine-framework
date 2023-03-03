package shine.framework.util.function;

import java.util.Optional;

/**
 * @author SilenceShine
 * @since 1.0
 */
@FunctionalInterface
public interface DiConsumer<T, U, V, W> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param v the third input argument
     * @param w the fourth input argument
     */
    void accept(T t, U u, V v, W w);

    /**
     * Returns a composed {@code BiConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code BiConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default DiConsumer<T, U, V, W> andThen(DiConsumer<? super T, ? super U, ? super V, ? super W> after) {
        return Optional.of(after)
                .map(diConsumer -> (DiConsumer<T, U, V, W>) (t, u, v, w) -> {
                    diConsumer.accept(t, u, v, w);
                    after.accept(t, u, v, w);
                })
                .get();
    }

}

