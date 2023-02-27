package shine.framework.util.function;

import java.util.Optional;

/**
 * 三个入参的consumer
 *
 * @author SilenceShine
 * @since 1.0
 */
@FunctionalInterface
public interface CiConsumer<T, U, V> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param v the third input argument
     */
    void accept(T t, U u, V v);

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
    default CiConsumer<T, U, V> andThen(CiConsumer<? super T, ? super U, ? super V> after) {
        return Optional.of(after)
                .map(ciConsumer -> (CiConsumer<T, U, V>) (t, u, v) -> {
                    ciConsumer.accept(t, u, v);
                    after.accept(t, u, v);
                })
                .get();
    }

}
