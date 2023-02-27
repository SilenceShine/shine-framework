package shine.framework.util.json.module;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import shine.framework.constant.DateConstant;

import java.io.Serial;
import java.time.LocalDateTime;
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

    public ShineModule() {
        super(VersionUtil.parseVersion(VERSION, GROUP_ID, ARTIFACT_ID));
        LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateConstant.PATTERN));
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateConstant.PATTERN));
        addSerializer(LocalDateTime.class, serializer);
        addDeserializer(LocalDateTime.class, deserializer);
    }

}
