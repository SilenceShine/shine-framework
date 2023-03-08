package shine.framework.spring.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import shine.framework.template.UidGenerateTemplate;

/**
 * @author SilenceShine
 * @since 1.0
 */
@Slf4j
@Component
public class QueueGenerate {

    private static final UidGenerateTemplate UID_TEMPLATE = new UidGenerateTemplate();

    /**
     * 获取自增uid
     */
    public static Long getUid() {
        return UID_TEMPLATE.getUid();
    }

}