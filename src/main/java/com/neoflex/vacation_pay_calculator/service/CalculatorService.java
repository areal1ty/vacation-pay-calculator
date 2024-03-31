package com.neoflex.vacation_pay_calculator.service;

import com.neoflex.vacation_pay_calculator.dto.PayResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculatorService {
    PayResponse calculateVacationPay(BigDecimal salary, int vacationDays);
    int calculatePaidDays(int vacationDays, LocalDate startDate);
}
