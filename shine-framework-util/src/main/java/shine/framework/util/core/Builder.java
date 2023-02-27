package shine.framework.util.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 最大支持5个参数的通用构造器
 *
 * @author SilenceShine
 * @since 1.0
 */
public final class Builder<T> {

    private final Supplier<T> supplier;

    private final List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instant) {
        this.supplier = instant;
    }

    public static <T> Builder<T> of(Supplier<T> instant) {
        return new Builder<>(instant);
    }

    public <P1> Builder<T> with(Consumer0<T> consumer) {
        Consumer<T> c = consumer::accept;
        modifiers.add(c);
        return this;
    }

    public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public <P1, P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4> Builder<T> with(Consumer4<T, P1, P2, P3, P4> consumer, P1 p1, P2 p2, P3 p3, P4 p4) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5> Builder<T> with(Consumer5<T, P1, P2, P3, P4, P5> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5);
        modifiers.add(c);
        return this;
    }

    public T build() {
        T value = supplier.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    @FunctionalInterface
    public interface Consumer0<T> {
        void accept(T t);
    }

    @FunctionalInterface
    public interface Consumer1<T, P1> {
        void accept(T t, P1 p1);
    }

    @FunctionalInterface
    public interface Consumer2<T, P1, P2> {
        void accept(T t, P1 p1, P2 p2);
    }

    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {
        void accept(T t, P1 p1, P2 p2, P3 p3);
    }

    @FunctionalInterface
    public interface Consumer4<T, P1, P2, P3, P4> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    @FunctionalInterface
    public interface Consumer5<T, P1, P2, P3, P4, P5> {
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

}
