package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeleteExpenseRequest {
    private String username;
    private int expenseId;
    private LocalDate month;
}
