package io.github.SilenceShine.shine.spring.orm.page;

import io.github.SilenceShine.shine.core.dto.PageModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class SpringDataPageUtil extends PageUtil {

    public static PageRequest pageSingle() {
        return PageRequest.of(1, 1);
    }

    public static PageRequest page(PageModel<?> model) {
        PageRequest page = page(model.getStart(), model.getLimit());
        sort(model, (isAsc, orders) -> {
            Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
            page.withSort(direction, orders.toArray(new String[0]));
        });
        return page;
    }

    public static PageRequest page(int start, int limit) {
        return PageRequest.of(start, limit);
    }

}
