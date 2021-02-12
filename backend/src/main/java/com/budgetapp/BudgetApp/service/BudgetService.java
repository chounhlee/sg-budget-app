package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.DeleteExpenseRequest;
import com.budgetapp.BudgetApp.model.Expense;

import java.util.List;

public interface BudgetService {
    List<Expense> getExpenses(String username);
    Expense createExpense(Expense expense);
    Expense updateExpense(Expense expense);
    boolean deleteExpense(DeleteExpenseRequest deleteExpenseRequest);

    void getIncomeAndFund();
    void editIncomeAndFund();
}
