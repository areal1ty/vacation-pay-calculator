package com.neoflex.vacation_pay_calculator.service;

import com.neoflex.vacation_pay_calculator.dto.PayResponse;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Getter
public class Calculator implements CalculatorService{
    private static final int currentYear = 2024;
    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;
    private static final double NDFL = 0.13;

    private final List<LocalDate> holidays = List.of(
            convertToDate(1, 1),
            convertToDate(1, 2),
            convertToDate(1, 3),
            convertToDate(1, 4),
            convertToDate(1, 5),
            convertToDate(1, 6),
            convertToDate(1, 7),
            convertToDate(1, 8),
            convertToDate(2, 23),
            convertToDate(2, 24),
            convertToDate(2, 25),
            convertToDate(3, 8),
            convertToDate(3, 9),
            convertToDate(4, 28),
            convertToDate(4, 29),
            convertToDate(4, 30),
            convertToDate(5, 1),
            convertToDate(5, 9),
            convertToDate(5, 10),
            convertToDate(5, 11),
            convertToDate(5, 12),
            convertToDate(11, 3),
            convertToDate(11, 4),
            convertToDate(12, 29),
            convertToDate(12, 30),
            convertToDate(12, 31)
    );

    @Override
    public PayResponse calculateVacationPay(BigDecimal salary, int vacationDays) {
        BigDecimal averageEarningsPerDay = salary.divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MONTH), 2, RoundingMode.HALF_EVEN);
        BigDecimal totalPayWithoutNDFL = averageEarningsPerDay.multiply(BigDecimal.valueOf(vacationDays));
        BigDecimal amountNDFL = totalPayWithoutNDFL.multiply(BigDecimal.valueOf(NDFL)).setScale(0, RoundingMode.HALF_UP);
        BigDecimal totalPay = totalPayWithoutNDFL.subtract(amountNDFL);

        return new PayResponse("Total vacation pay with NDFL = ", totalPay);
    }

    public int calculatePaidDays(int vacationDays, LocalDate startDate) {
        Predicate<LocalDate> holidays = getHolidays()::contains;

        return (int) Stream.iterate(startDate, nextVacationDate -> nextVacationDate.plusDays(1))
                .limit(vacationDays)
                .filter(vacationDate -> !(holidays.test(vacationDate)))
                .filter(vacationDate -> !(vacationDate.getDayOfWeek() == DayOfWeek.SATURDAY || vacationDate.getDayOfWeek() == DayOfWeek.SUNDAY))
                .count();
    }

    private LocalDate convertToDate(int month, int day) {
        return LocalDate.of(currentYear, month, day);
    }

}
