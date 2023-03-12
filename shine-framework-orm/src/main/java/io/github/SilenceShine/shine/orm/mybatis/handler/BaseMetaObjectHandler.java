package io.github.SilenceShine.shine.orm.mybatis.handler;

import io.github.SilenceShine.shine.orm.mybatis.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class BaseMetaObjectHandler extends MultipleMetaObjectHandler<BaseEntity> {

    public BaseMetaObjectHandler() {
        super(BaseEntity.class);
    }

    @Override
    public void fillInsert(BaseEntity baseEntity) {
        baseEntity.setCreatedTime(LocalDateTime.now());
    }

    @Override
    public void fillUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdatedTime(LocalDateTime.now());
    }

}
