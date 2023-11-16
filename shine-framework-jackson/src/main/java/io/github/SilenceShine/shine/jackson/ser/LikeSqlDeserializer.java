package io.github.SilenceShine.shine.jackson.ser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import io.github.SilenceShine.shine.util.core.StrUtils;

import java.io.IOException;
import java.io.Serial;

/**
 * 数据库sql 排除特殊字符 转义处理
 *
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
public class LikeSqlDeserializer extends StringDeserializer {

    @Serial
    private static final long serialVersionUID = -6931693896864817046L;

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String str = super.deserialize(p, ctxt);
        return StrUtils.sqlEscape(str);
    }

}
