package io.github.SilenceShine.shine.spring.orm.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.SilenceShine.shine.core.dto.PageModel;
import io.github.SilenceShine.shine.util.log.LogUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Slf4j
public class MybatisPageUtil extends PageUtil {

    public static <M> IPage<M> pageSingle() {
        return Page.of(1, 1);
    }

    public static <M> IPage<M> page(int start, int limit) {
        return Page.of(start, limit);
    }

    public static <M> IPage<M> page(PageModel<?> model) {
        IPage<M> page = page(model.getStart(), model.getLimit());
        sort(model, (isAsc, orders) -> {
            for (String order : orders) {
                OrderItem item = isAsc ? OrderItem.asc(order) : OrderItem.desc(order);
                page.orders().add(item);
            }
        });
        return page;
    }

    public static <T> void recursion(IPage<T> page, Function<IPage<T>, IPage<T>> function, Consumer<List<T>> consumer) {
        LogUtil.debug(log, "mybatis current:{} size:{} total:{}", page.getCurrent(), page.getSize(), page.getTotal());
        IPage<T> iPage = function.apply(page);
        consumer.accept(iPage.getRecords());
        if (iPage.getCurrent() >= iPage.getPages()) return;
        page.setCurrent(iPage.getCurrent() + 1);
        recursion(iPage, function, consumer);
    }

}
