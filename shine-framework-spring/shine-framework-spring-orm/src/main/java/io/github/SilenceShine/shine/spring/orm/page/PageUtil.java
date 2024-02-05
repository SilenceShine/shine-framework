package io.github.SilenceShine.shine.spring.orm.page;

import io.github.SilenceShine.shine.core.dto.PageModel;
import io.github.SilenceShine.shine.util.core.StrUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Slf4j
public class PageUtil {

    public static void sort(PageModel<?> model, BiConsumer<Boolean, List<String>> consumer) {
        String orderBy = model.getOrderBy();
        List<String> orders = StrUtils.split(orderBy, StrUtils.COMMA);
        if (orders.isEmpty()) return;
        consumer.accept(model.getIsAsc(), orders);
    }

}
