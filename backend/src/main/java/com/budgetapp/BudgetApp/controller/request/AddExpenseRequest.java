package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AddExpenseRequest {
    private String username;
    private String expenseName;
    private boolean isMonthly;
    private BigDecimal amount;
    private LocalDate month;
}
