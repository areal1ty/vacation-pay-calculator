package com.neoflex.vacation_pay_calculator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayResponse {
    private String message;
    private BigDecimal vacationPay;
}
