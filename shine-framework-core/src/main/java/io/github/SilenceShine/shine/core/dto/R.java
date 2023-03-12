package io.github.SilenceShine.shine.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 公共返回
 *
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract sealed class R<E> implements Serializable permits SingleR, PageR, MultiR {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 是否操作成功
     */
    private boolean success;

    /**
     * 系统状态状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 具体数据体
     */
    protected E data;

}
