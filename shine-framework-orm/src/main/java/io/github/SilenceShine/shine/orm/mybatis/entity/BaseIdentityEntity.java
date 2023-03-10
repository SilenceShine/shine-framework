package io.github.SilenceShine.shine.orm.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.SilenceShine.shine.orm.mybatis.handler.MultipleMetaObjectHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * id entity 通用填充类 {@link  MultipleMetaObjectHandler}
 *
 * @author SilenceShine
 * @since 1.0
 */
@Getter
@Setter
@TableName(keepGlobalPrefix = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseIdentityEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -614979473626580074L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

}
