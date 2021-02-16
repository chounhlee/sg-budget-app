package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDeleteRequest {
    private String username;
    private int expenseId;
    private LocalDate month;
}
