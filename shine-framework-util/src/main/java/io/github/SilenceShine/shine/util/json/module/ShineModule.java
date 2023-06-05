package io.github.SilenceShine.shine.util.json.module;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.github.SilenceShine.shine.constant.DateConstant;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author SilenceShine
 * @since 1.0
 */
public final class ShineModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = 2584419732278956712L;
    private static final String VERSION = "0.3.1";
    private static final String GROUP_ID = "io.github.SilenceShine";
    private static final String ARTIFACT_ID = "shine-framework-util";

    public static final ShineModule INSTANCE = new ShineModule();

    public ShineModule() {
        super(VersionUtil.parseVersion(VERSION, GROUP_ID, ARTIFACT_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateConstant.PATTERN);

        LocalTimeSerializer localTimeSerializer = new LocalTimeSerializer(formatter);
        LocalDateSerializer localDateSerializer = new LocalDateSerializer(formatter);
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(formatter);
        addSerializer(LocalTime.class, localTimeSerializer);
        addSerializer(LocalDate.class, localDateSerializer);
        addSerializer(LocalDateTime.class, localDateTimeSerializer);

        LocalTimeDeserializer localTimeDeserializer = new LocalTimeDeserializer(formatter);
        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(formatter);
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
        addDeserializer(LocalTime.class, localTimeDeserializer);
        addDeserializer(LocalDate.class, localDateDeserializer);
        addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
    }

}
