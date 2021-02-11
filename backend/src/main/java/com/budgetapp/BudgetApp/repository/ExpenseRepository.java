package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.model.Expense;

import java.util.List;

public interface ExpenseRepository {
    List<Expense> getExpenses(String username);
}
