package shine.framework.template;

/**
 * @author SilenceShine
 * @since 1.0
 */
public interface QueueTemplate<E, F> {

    /**
     * 获取单个元素
     *
     * @param type 类型
     * @return E
     */
    E getElement(String type);

    /**
     * 获取元素集合
     *
     * @param type 类型
     * @param size 个数
     * @return F
     */
    F getElements(String type, int size);

}