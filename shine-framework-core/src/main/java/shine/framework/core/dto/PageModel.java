package shine.framework.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页Model
 *
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
public abstract class PageModel<T> {

    /**
     * 分页开始
     */
    private Integer start = 1;

    /**
     * 条数
     */
    private Integer limit = 10;

    /**
     * 是否正序
     */
    private Boolean isAsc;

    /**
     * 排序字段 使用","隔开
     */
    private String orderBy;

}