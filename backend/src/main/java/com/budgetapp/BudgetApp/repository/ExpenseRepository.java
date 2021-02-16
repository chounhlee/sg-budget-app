package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.model.Expense;

import java.util.List;

public interface ExpenseRepository {
    List<Expense> getExpenses(String username);
    Expense addExpense(Expense expense);
    boolean updateExpense(Expense expense);
    boolean deleteExpense(int id);
    Expense getExpense(String username, int id);
}
