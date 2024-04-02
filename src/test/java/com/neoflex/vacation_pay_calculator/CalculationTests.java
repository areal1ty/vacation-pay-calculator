package com.neoflex.vacation_pay_calculator;

import com.neoflex.vacation_pay_calculator.service.Calculator;
import com.neoflex.vacation_pay_calculator.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class CalculationTests {

    private CalculatorService calculator;

    private final BigDecimal yearSalary = new BigDecimal("50000.00");
    private final int vacationDays = 28;

    @BeforeEach
    void init() {
        log.info("initializing calculator");
        calculator = new Calculator();
    }

    @AfterEach
    public void teardown() {
        calculator = null;
    }

    @Test
    @DisplayName("Vacation pay calculation")
    void calculateVacationPay() {
        assertEquals(BigDecimal.valueOf(29692.60).stripTrailingZeros(),
                calculator.calculateVacationPay(yearSalary,
                                calculator.calculatePaidDays(vacationDays, LocalDate.of(2024, 3, 31)))
                        .getVacationPay().stripTrailingZeros());
    }

}
