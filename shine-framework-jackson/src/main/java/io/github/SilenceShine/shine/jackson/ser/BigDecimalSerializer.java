package io.github.SilenceShine.shine.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author SilenceShine
 * @since 1.0
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    public static final BigDecimalSerializer INSTANCE = new BigDecimalSerializer();

    @Override
    public Class<BigDecimal> handledType() {
        return BigDecimal.class;
    }

    @Override
    public void serialize(BigDecimal decimal, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (decimal != null) {
            gen.writeString(decimal.setScale(2, RoundingMode.HALF_UP).toString());
        }
    }

}