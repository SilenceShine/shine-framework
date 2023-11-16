package io.github.SilenceShine.shine.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Getter
public class DefaultExcelReadListener<E extends DefaultExcelData> extends AnalysisEventListener<E> {

    private final List<E> list = new ArrayList<>();

    @Override
    public void invoke(E element, AnalysisContext context) {
        element.setRow(context.readRowHolder().getRowIndex() + 1);
        list.add(element);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public int getRow(AnalysisContext context) {
        return context.readRowHolder().getRowIndex() + 1;
    }

}
