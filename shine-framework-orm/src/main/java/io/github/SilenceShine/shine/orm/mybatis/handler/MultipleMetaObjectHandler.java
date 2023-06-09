package io.github.SilenceShine.shine.orm.mybatis.handler;

import org.apache.ibatis.reflection.MetaObject;

import java.util.List;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class MultipleMetaObjectHandler extends AbstractMetaObjectHandler<Object> {

    private final List<AbstractMetaObjectHandler> handlers;

    public MultipleMetaObjectHandler(List<AbstractMetaObjectHandler> handlers) {
        super(null);
        this.handlers = handlers;
    }

    @Override
    protected boolean check(MetaObject metaObject) {
        return true;
    }

    @Override
    public void fillInsert(Object o) {
        handlers.forEach(handler -> handler.fillInsert(o));
    }

    @Override
    public void fillUpdate(Object o) {
        handlers.forEach(handler -> handler.fillUpdate(o));
    }

}
