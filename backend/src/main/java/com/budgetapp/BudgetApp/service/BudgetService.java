package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.model.Expense;

import java.util.List;

public interface BudgetService {
    List<Expense> getExpenses(String username);
    void createExpense();
    void updateExpense();
    void deleteExpense();

    void getIncomeAndFund();
    void editIncomeAndFund();
}
