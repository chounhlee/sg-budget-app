package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserUpdateIncomeAndFundRequest {
    private String username;
    private BigDecimal monthlyIncome;
    private BigDecimal availableFund;
    private LocalDate month;
}
