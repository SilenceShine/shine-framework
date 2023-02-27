package shine.framework.util.function;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 条件工具类
 *
 * @author SilenceShine
 * @since 1.0
 */
public final class Condition<T> {

    private final List<Predicate<T>> predicates;

    @SafeVarargs
    public Condition(Predicate<T>... predicate) {
        predicates = List.of(predicate);
    }

    @SafeVarargs
    public static <T> Condition<T> of(Predicate<T>... predicates) {
        return new Condition<>(predicates);
    }

    public void isTrue(T value, Consumer<? super T> consumer) {
        Optional<Predicate<T>> optional = predicates.stream()
                .filter(predicate -> !predicate.test(value))
                .findAny();
        if (optional.isEmpty()) {
            consumer.accept(value);
        }
    }

    public static <T> void deal(Predicate<T> predicate, T value, Consumer<T> consumer) {
        Condition.of(predicate).isTrue(value, consumer);
    }

}
