package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class ExpenseDeleteRequest {
    private String username;
    private int expenseId;
    private LocalDate month;
}
