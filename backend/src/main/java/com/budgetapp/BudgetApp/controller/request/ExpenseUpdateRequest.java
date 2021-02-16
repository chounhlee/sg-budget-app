package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseUpdateRequest {
    private int expenseId;
    private String expenseName;
    private String username;
    private boolean isMonthly;
    private BigDecimal amount;
    private BigDecimal allocated;
    private LocalDate month;
}
