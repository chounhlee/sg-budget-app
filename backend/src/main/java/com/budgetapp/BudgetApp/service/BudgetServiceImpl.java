package com.budgetapp.BudgetApp.service;

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
    public void createExpense() {

    }

    @Override
    public void updateExpense() {

    }

    @Override
    public void deleteExpense() {

    }

    @Override
    public void getIncomeAndFund() {

    }

    @Override
    public void editIncomeAndFund() {

    }
}
