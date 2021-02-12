package com.budgetapp.BudgetApp.dto;

import com.budgetapp.BudgetApp.model.Expense;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ExpenseDto {
    private LocalDate month;
    private List<Expense> data;
}
