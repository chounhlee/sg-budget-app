package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.AddExpenseRequest;
import com.budgetapp.BudgetApp.controller.request.DeleteExpenseRequest;
import com.budgetapp.BudgetApp.controller.request.UpdateExpenseRequest;
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
    public Expense addExpense(AddExpenseRequest addExpenseRequest) {
        Expense expense = new Expense();
        expense.setUsername(addExpenseRequest.getUsername());
        expense.setAmount(addExpenseRequest.getAmount());
        expense.setExpenseName(addExpenseRequest.getExpenseName());
        expense.setRemaining(addExpenseRequest.getAmount());
        expense.setMonthly(addExpenseRequest.isMonthly());
        expense.setAllocated(BigDecimal.ZERO);
        expense.setDateUpdated(addExpenseRequest.getMonth());

        return expenseRepository.addExpense(expense);
    }

    @Override
    public boolean updateExpense(UpdateExpenseRequest updateExpenseRequest) {
        Expense expense = new Expense();
        expense.setId(updateExpenseRequest.getExpenseId());
        expense.setUsername(updateExpenseRequest.getUsername());
        expense.setAmount(updateExpenseRequest.getAmount());
        expense.setExpenseName(updateExpenseRequest.getExpenseName());
        expense.setMonthly(updateExpenseRequest.isMonthly());
        expense.setAllocated(updateExpenseRequest.getAllocated());
        expense.setDateUpdated(updateExpenseRequest.getMonth());

        // Need to calculate correctly
        expense.setRemaining(updateExpenseRequest.getAmount());

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
