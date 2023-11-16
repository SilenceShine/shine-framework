package io.github.SilenceShine.shine.constant;

import java.util.TimeZone;

/**
 * 时间常量
 *
 * @author SilenceShine
 * @since 1.0
 */
public interface DateConstant {

    long MS = 1;
    long SECOND = 1000 * MS;
    long MINUTE = 60 * SECOND;
    long HOUR = 60 * MINUTE;
    long DAY = 24 * HOUR;
    long WEEK = 7 * DAY;

    TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+8");

}
