package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.AddExpenseRequest;
import com.budgetapp.BudgetApp.controller.request.DeleteExpenseRequest;
import com.budgetapp.BudgetApp.controller.request.UpdateExpenseRequest;
import com.budgetapp.BudgetApp.model.Expense;

import java.util.List;

public interface BudgetService {
    List<Expense> getExpenses(String username);
    Expense addExpense(AddExpenseRequest addExpenseRequest);
    boolean updateExpense(UpdateExpenseRequest updateExpenseRequest);
    boolean deleteExpense(DeleteExpenseRequest deleteExpenseRequest);

    void getIncomeAndFund();
    void editIncomeAndFund();
}
