package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class ExpenseAllocateRequest {
    private int expenseId;
    private String username;
    private BigDecimal allocated;
    private LocalDate month;
}
