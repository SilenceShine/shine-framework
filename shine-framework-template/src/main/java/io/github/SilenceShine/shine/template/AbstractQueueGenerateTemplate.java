package io.github.SilenceShine.shine.template;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author SilenceShine
 * @since 1.0
 */
public abstract class AbstractQueueGenerateTemplate<Q> implements QueueTemplate<Q, List<Q>> {

    protected static final int DEFAULT_SIZE = 1000;

    protected ConcurrentHashMap<String, ConcurrentLinkedQueue<Q>> queueMap = new ConcurrentHashMap<>();

    @Override
    public abstract List<Q> getElements(String type, int size);

    @Override
    public Q getElement(String type) {
        ConcurrentLinkedQueue<Q> queue = queueMap.computeIfAbsent(type, __ -> new ConcurrentLinkedQueue<>());
        Q element = queue.poll();
        if (element == null) {
            synchronized (this) {
                element = queue.poll();
                if (element != null) {
                    return element;
                }
                List<Q> elements = getElements(type, DEFAULT_SIZE);
                elements.forEach(queue::offer);
                return getElement(type);
            }
        }
        return element;
    }

}
