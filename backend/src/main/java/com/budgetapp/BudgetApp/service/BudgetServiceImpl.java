package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.ExpenseAddRequest;
import com.budgetapp.BudgetApp.controller.request.ExpenseDeleteRequest;
import com.budgetapp.BudgetApp.controller.request.ExpenseUpdateRequest;
import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    final
    ExpenseRepository expenseRepository;

    public BudgetServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getExpenses(String username) {
        return expenseRepository.getExpenses(username);
    }

    @Override
    public Expense addExpense(ExpenseAddRequest expenseAddRequest) {
        Expense expense = new Expense();
        expense.setUsername(expenseAddRequest.getUsername());
        expense.setAmount(expenseAddRequest.getAmount());
        expense.setExpenseName(expenseAddRequest.getExpenseName());
        expense.setRemaining(expenseAddRequest.getAmount());
        expense.setMonthly(expenseAddRequest.isMonthly());
        expense.setAllocated(BigDecimal.ZERO);
        expense.setDateUpdated(expenseAddRequest.getMonth());

        return expenseRepository.addExpense(expense);
    }

    @Override
    public boolean updateExpense(ExpenseUpdateRequest expenseUpdateRequest) {
        Expense expense = new Expense();
        expense.setId(expenseUpdateRequest.getExpenseId());
        expense.setUsername(expenseUpdateRequest.getUsername());
        expense.setAmount(expenseUpdateRequest.getAmount());
        expense.setExpenseName(expenseUpdateRequest.getExpenseName());
        expense.setMonthly(expenseUpdateRequest.isMonthly());
        expense.setAllocated(expenseUpdateRequest.getAllocated());
        expense.setDateUpdated(expenseUpdateRequest.getMonth());

        // Need to calculate correctly
        expense.setRemaining(expenseUpdateRequest.getAmount());

        // Update user fund

        return expenseRepository.updateExpense(expense);
    }

    @Override
    public boolean deleteExpense(ExpenseDeleteRequest expenseDeleteRequest) {
        return expenseRepository.deleteExpense(expenseDeleteRequest.getExpenseId());
    }
}
