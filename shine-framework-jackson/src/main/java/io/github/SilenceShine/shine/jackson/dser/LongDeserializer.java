package io.github.SilenceShine.shine.jackson.dser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.SilenceShine.shine.core.exception.BizException;
import io.github.SilenceShine.shine.jackson.exception.JacksonException;

import java.io.IOException;

/**
 * long 转 string
 *
 * @author SilenceShine
 * @since 1.0
 */
public class LongDeserializer extends JsonDeserializer<Long> {

    public static final LongDeserializer INSTANCE = new LongDeserializer();

    @Override
    public Class<?> handledType() {
        return Long.class;
    }

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return Long.parseLong(p.getValueAsString());
        } catch (Exception e) {
            throw new BizException(JacksonException.LONG_DESERIALIZER, p.getValueAsString());
        }
    }

}