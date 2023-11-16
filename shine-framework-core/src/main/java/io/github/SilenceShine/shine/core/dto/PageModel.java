package io.github.SilenceShine.shine.core.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * 分页Model
 *
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public abstract class PageModel<T> {

    /**
     * 分页开始
     */
    @Min(value = 1, message = "start只能从1开始")
    @NotNull(message = "start不能为空")
    Integer start = 1;

    /**
     * 条数
     */
    @Min(value = 1, message = "limit只能从1开始")
    @NotNull(message = "start不能为空")
    Integer limit = 10;

    /**
     * 是否正序
     */
    Boolean isAsc = true;

    /**
     * 排序字段 使用","隔开
     */
    String orderBy;

}
