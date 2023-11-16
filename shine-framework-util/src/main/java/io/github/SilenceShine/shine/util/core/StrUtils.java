package io.github.SilenceShine.shine.util.core;

import cn.hutool.core.util.StrUtil;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
public class StrUtils extends StrUtil {

    /**
     * 数据库字符串特殊字符转移
     *
     * @param sql sql字符串
     * @return 转义后的字符串
     */
    public static String sqlEscape(String sql) {
        if (isNotEmpty(sql)) {
            sql = sql.replaceAll("\\\\", "\\\\\\\\");
            sql = sql.replaceAll("_", "\\\\_");
            sql = sql.replaceAll("%", "\\\\%");
        }
        return sql;
    }

}
