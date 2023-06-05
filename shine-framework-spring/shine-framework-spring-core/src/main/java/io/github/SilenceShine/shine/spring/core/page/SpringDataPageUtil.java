package io.github.SilenceShine.shine.spring.core.page;

import org.springframework.data.domain.PageRequest;
import io.github.SilenceShine.shine.core.dto.PageModel;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class SpringDataPageUtil {

    public static PageRequest pageOne() {
        return buildPage(1, 1);
    }

    public static PageRequest buildPage(PageModel<?> model) {
        return buildPage(model.getStart(), model.getLimit());
    }

    public static PageRequest buildPage(int start, int limit) {
        return PageRequest.of(start, limit);
    }

}
