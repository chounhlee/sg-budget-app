package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class ExpenseAddRequest {
    private String username;
    private String expenseName;
    private boolean isMonthly;
    private BigDecimal amount;
    private LocalDate month;
}
