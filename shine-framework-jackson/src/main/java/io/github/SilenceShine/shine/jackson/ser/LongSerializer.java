package io.github.SilenceShine.shine.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class LongSerializer extends JsonSerializer<Long> {

    public static final LongSerializer INSTANCE = new LongSerializer();

    @Override
    public Class<Long> handledType() {
        return Long.class;
    }

    @Override
    public void serialize(Long param, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (param != null) {
            gen.writeString(param.toString());
        }
    }

}