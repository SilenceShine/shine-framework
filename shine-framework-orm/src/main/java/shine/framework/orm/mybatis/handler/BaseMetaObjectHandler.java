package shine.framework.orm.mybatis.handler;

import shine.framework.orm.mybatis.entity.BaseEntity;

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

    }

    @Override
    public void fillUpdate(BaseEntity baseEntity) {

    }

}
