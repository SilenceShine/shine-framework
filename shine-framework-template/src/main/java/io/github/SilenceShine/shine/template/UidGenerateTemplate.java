package io.github.SilenceShine.shine.template;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import io.github.SilenceShine.shine.core.exception.BizException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author SilenceShine
 * @since 1.0
 */
@Slf4j
public class UidGenerateTemplate extends AbstractQueueGenerateTemplate<Long> {

    private static final String SNOWFLAKE = "SNOWFLAKE";

    @Override
    public List<Long> getElements(String type, int size) {
        if (SNOWFLAKE.equals(type)) {
            return Stream.generate(IdUtil::getSnowflakeNextId)
                    .limit(size)
                    .collect(Collectors.toList());
        }
        throw new BizException(TemplateException.TEMPLATE_TYPE);
    }

    @Override
    public Long getElement(String type) {
        if (SNOWFLAKE.equals(type)) return IdUtil.getSnowflakeNextId();
        throw new BizException(TemplateException.TEMPLATE_TYPE);
    }

    public Long getUid() {
        return getElement(SNOWFLAKE);
    }

}
