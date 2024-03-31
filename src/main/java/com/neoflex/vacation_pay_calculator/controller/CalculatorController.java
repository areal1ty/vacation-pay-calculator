package com.neoflex.vacation_pay_calculator.controller;

import com.neoflex.vacation_pay_calculator.dto.PayResponse;
import com.neoflex.vacation_pay_calculator.service.Calculator;
import com.neoflex.vacation_pay_calculator.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class CalculatorController {
    private final CalculatorService payCalculatorService;

    @GetMapping("/calculate")
    public PayResponse getVacationPay(@RequestParam("salary") BigDecimal salary,
                                      @RequestParam("vacationDays") int days,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate) {
        if (startDate.isPresent()) {
            days = payCalculatorService.calculatePaidDays(days, startDate.get());
        }
        return payCalculatorService.calculateVacationPay(salary, days);
    }
    }