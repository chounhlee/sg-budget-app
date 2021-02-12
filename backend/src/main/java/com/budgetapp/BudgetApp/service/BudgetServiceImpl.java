package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.DeleteExpenseRequest;
import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Expense createExpense(Expense expense) {
        return expenseRepository.addExpense(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {

        // Need to calculate correctly
        expense.setRemaining(expense.getAmount());

        // Update user fund

        return expenseRepository.updateExpense(expense);
    }

    @Override
    public boolean deleteExpense(DeleteExpenseRequest deleteExpenseRequest) {
        return expenseRepository.deleteExpense(deleteExpenseRequest.getExpenseId());
    }

    @Override
    public void getIncomeAndFund() {

    }

    @Override
    public void editIncomeAndFund() {

    }
}
