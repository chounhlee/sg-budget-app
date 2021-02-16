package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.ExpenseAddRequest;
import com.budgetapp.BudgetApp.controller.request.ExpenseAllocateRequest;
import com.budgetapp.BudgetApp.controller.request.ExpenseDeleteRequest;
import com.budgetapp.BudgetApp.controller.request.ExpenseUpdateRequest;
import com.budgetapp.BudgetApp.model.Expense;

import java.util.List;

public interface BudgetService {
    List<Expense> getExpenses(String username);
    Expense addExpense(ExpenseAddRequest expenseAddRequest);
    boolean updateExpense(ExpenseUpdateRequest expenseUpdateRequest);
    boolean deleteExpense(ExpenseDeleteRequest expenseDeleteRequest);
    boolean allocateExpense(ExpenseAllocateRequest expenseAllocateRequest);
}
