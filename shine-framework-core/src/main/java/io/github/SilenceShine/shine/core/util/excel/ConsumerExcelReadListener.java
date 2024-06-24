package io.github.SilenceShine.shine.core.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import io.github.SilenceShine.shine.core.util.ValidatorUtil;
import io.github.SilenceShine.shine.util.excel.DefaultExcelData;
import io.github.SilenceShine.shine.util.excel.DefaultExcelReadListener;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
public class ConsumerExcelReadListener<E extends DefaultExcelData> extends DefaultExcelReadListener<E> {

    private final BiFunction<Integer, String, String> messageFunction;
    private final Consumer<E> consumer;

    public ConsumerExcelReadListener(BiFunction<Integer, String, String> messageFunction, Consumer<E> consumer) {
        this.messageFunction = messageFunction;
        this.consumer = consumer;
    }

    @Override
    public void invoke(E element, AnalysisContext context) {
        ValidatorUtil.validate(element, violation -> messageFunction.apply(getRow(context), violation.getMessage()));
        consumer.accept(element);
        super.invoke(element, context);
    }

}
