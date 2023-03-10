package shine.framework.orm.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author SilenceShine
 * @since 1.0
 */
@Slf4j
@AllArgsConstructor
public abstract class MultipleMetaObjectHandler<T> implements MetaObjectHandler {

    private final Class<T> tClass;

    public abstract void fillInsert(T t);

    public abstract void fillUpdate(T t);

    protected boolean check(MetaObject metaObject) {
        return tClass.isAssignableFrom(metaObject.getClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insertFill(MetaObject metaObject) {
        if (tClass.isAssignableFrom(metaObject.getClass())) {
            fillInsert((T) metaObject);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateFill(MetaObject metaObject) {
        if (tClass.isAssignableFrom(metaObject.getClass())) {
            fillUpdate((T) metaObject);
        }
    }

}
