package shine.framework.util.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import shine.framework.util.log.LogUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author SilenceShine
 * @since 1.0
 */
@Slf4j
public class JacksonUtilsTest {

    public static void main(String[] args) {
        test();
//        override();
    }

    public static void test() {
        LocalDateTime now = LocalDateTime.now();
        LogUtil.info(JacksonUtilsTest.class, "now:{}", JacksonUtils.toJson(now));
        System.out.println(JacksonUtils.toJson(now));
    }

    public static void override() {
        Version version = VersionUtil.parseVersion("0.0.1", "io.github.SilenceShine", "shine-framework-util-test");
        SimpleModule simpleModule = new SimpleModule(version);
        LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        simpleModule.addSerializer(LocalDateTime.class, serializer);
        JacksonUtils.addModule(simpleModule);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(JacksonUtils.toJson(now));
    }

}