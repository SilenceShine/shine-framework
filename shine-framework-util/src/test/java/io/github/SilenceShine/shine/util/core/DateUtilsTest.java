package io.github.SilenceShine.shine.util.core;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

class DateUtilsTest {

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 1);
        DateUtils.getAscDays(startDate, endDate).forEach(System.out::println);

        YearMonth startYearMonth = YearMonth.of(2020, 1);
        YearMonth endYearMonth = YearMonth.of(2021, 1);
        DateUtils.getAscMonths(startYearMonth, endYearMonth).forEach(System.out::println);

        Year startYear = Year.of(2020);
        Year endYear = Year.of(2021);
        DateUtils.getAscYears(startYear, endYear).forEach(System.out::println);


    }

}
