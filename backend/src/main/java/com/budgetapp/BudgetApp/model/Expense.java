package com.budgetapp.BudgetApp.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Expense {
    private int id;
    private String username;
    private String expenseName;
    private boolean isMonthly;
    private BigDecimal amount;
    private BigDecimal allocated;
    private BigDecimal remaining;
    private LocalDate dateUpdated;
}
