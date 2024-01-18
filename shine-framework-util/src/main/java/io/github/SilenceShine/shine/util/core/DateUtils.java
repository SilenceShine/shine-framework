package io.github.SilenceShine.shine.util.core;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class DateUtils extends DateUtil {

    public static List<LocalDateTime> getAscSeconds(LocalDateTime start, LocalDateTime end) {
        return getAscTemporals(start, end, LocalDateTime::plusSeconds, LocalDateTime::isBefore);
    }

    public static List<LocalDateTime> getAscMinutes(LocalDateTime start, LocalDateTime end) {
        return getAscTemporals(start, end, LocalDateTime::plusMinutes, LocalDateTime::isBefore);
    }

    public static List<LocalDateTime> getAscHours(LocalDateTime start, LocalDateTime end) {
        return getAscTemporals(start, end, LocalDateTime::plusHours, LocalDateTime::isBefore);
    }

    public static List<LocalDateTime> getAscWeeks(LocalDateTime start, LocalDateTime end) {
        return getAscTemporals(start, end, LocalDateTime::plusWeeks, LocalDateTime::isBefore);
    }

    public static List<Year> getAscYears(Year start, Year end) {
        return getAscTemporals(start, end, Year::plusYears, Year::isBefore);
    }

    public static List<YearMonth> getAscMonths(YearMonth start, YearMonth end) {
        return getAscTemporals(start, end, YearMonth::plusMonths, YearMonth::isBefore);
    }

    public static List<LocalDate> getAscDays(LocalDate start, LocalDate end) {
        return getAscTemporals(start, end, LocalDate::plusDays, LocalDate::isBefore);
    }

    /**
     * 获取两个 Temporal 之间包含了多少个 Temporal
     *
     * @param start           开始 Temporal
     * @param end             结束 Temporal
     * @param function        偏移单位function
     * @param compareFunction 比较function
     * @param <T>             Temporal
     * @return List<T>
     */
    public static <T extends Temporal> List<T> getAscTemporals(T start, T end, BiFunction<T, Integer, T> function, BiFunction<T, T, Boolean> compareFunction) {
        if (compareFunction.apply(end, start)) {
            throw new IllegalArgumentException("start Temporal must before Temporal end!");
        }
        List<T> result = new ArrayList<>();
        result.add(start);
        while (compareFunction.apply(start, end)) {
            start = function.apply(start, 1);
            result.add(start);
        }
        return result;
    }

}
