package com.budgetapp.BudgetApp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserIncomeAndExpenseDto {
    private BigDecimal monthlyIncome;
    private BigDecimal availableFund;
}
