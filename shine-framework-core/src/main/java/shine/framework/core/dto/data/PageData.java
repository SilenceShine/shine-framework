package shine.framework.core.dto.data;

import lombok.Getter;
import lombok.Setter;
import shine.framework.util.core.Builder;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 分页data
 *
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
public class PageData<E> {

    private long total = 0L;
    private long page = 1L;
    private long size = 10L;

    private Collection<E> list;

    public List<E> getList() {
        return null == list ? Collections.emptyList() : new ArrayList<>(list);
    }

    @Transient
    public long getTotalPages() {
        return this.total % this.size == 0
                ? this.total / this.size
                : (this.total / this.size) + 1;
    }

    @Transient
    public boolean isEmpty() {
        return null == list || list.size() == 0;
    }

    @Transient
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static <T> PageData<T> of(long page, long size) {
        return of(0L, page, size, Collections.emptyList());
    }

    public static <T> PageData<T> of(long total, long page, long size, Collection<T> data) {
        return Builder.of(PageData<T>::new)
                .with(PageData::setTotal, total)
                .with(PageData::setPage, page)
                .with(PageData::setSize, size)
                .with(PageData::setList, data)
                .build();
    }

}
