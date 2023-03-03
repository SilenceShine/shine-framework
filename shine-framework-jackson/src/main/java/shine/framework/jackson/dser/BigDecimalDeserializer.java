package shine.framework.jackson.dser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import shine.framework.core.exception.BizException;
import shine.framework.jackson.exception.JacksonException;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * BigDecimal反序列化类
 *
 * @author SilenceShine
 * @since 1.0
 */
public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

    public static final BigDecimalDeserializer INSTANCE = new BigDecimalDeserializer();

    @Override
    public Class<?> handledType() {
        return BigDecimal.class;
    }

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return new BigDecimal(p.getValueAsString());
        } catch (Exception e) {
            throw new BizException(JacksonException.BIG_DECIMAL_DESERIALIZER, p.getValueAsString());
        }
    }

}
