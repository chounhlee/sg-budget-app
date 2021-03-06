package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class UserUpdateIncomeAndFundRequest {
    private String username;
    private BigDecimal monthlyIncome;
    private BigDecimal availableFund;
    private LocalDate month;
}
