package com.neoflex.vacation_pay_calculator;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@Slf4j
public class CalculationMockMVCTest extends AbstractResolversCommonConfig {
    public final static String VACATION_PAY_API = "/calculate";

    @Autowired
    protected MockMvc mockMvc;

    @Test
    @Operation(summary = "Test for vacation pay salary calculating")
    void calculationOfVacationPayForEmployeeUsingQueryWithDateTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(VACATION_PAY_API)
                        .param("salary", String.valueOf(50000))
                        .param("vacationDays", String.valueOf(28))
                        .param("startDate", "2024-03-31")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationPay").value(BigDecimal.valueOf(29692.60).stripTrailingZeros()))
                .andReturn();

        log.info(result.getResponse().getContentAsString());
    }
}
