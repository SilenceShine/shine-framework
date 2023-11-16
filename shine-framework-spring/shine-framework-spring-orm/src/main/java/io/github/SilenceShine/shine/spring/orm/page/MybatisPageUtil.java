package io.github.SilenceShine.shine.spring.orm.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.SilenceShine.shine.core.dto.PageModel;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
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

}
