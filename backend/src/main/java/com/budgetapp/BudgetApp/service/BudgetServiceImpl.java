package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.*;
import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.repository.ExpenseRepository;
import com.budgetapp.BudgetApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    final
    private ExpenseRepository expenseRepository;

    final
    private UserRepository userRepository;

    public BudgetServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
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
        // Query for expense using expenseID
        Expense expenseFromRepo = expenseRepository.getExpense(
                expenseUpdateRequest.getUsername(), expenseUpdateRequest.getExpenseId());

        User userFromRepo = userRepository.getUserByUsername(expenseUpdateRequest.getUsername());
        userFromRepo.setAvailableFund(userFromRepo.getAvailableFund().add(expenseFromRepo.getAllocated()));

        expenseFromRepo.setAmount(expenseUpdateRequest.getAmount());
        expenseFromRepo.setRemaining(expenseUpdateRequest.getAmount());
        expenseFromRepo.setAllocated(new BigDecimal(0));
        expenseFromRepo.setExpenseName(expenseUpdateRequest.getExpenseName());
        expenseFromRepo.setMonthly(expenseUpdateRequest.isMonthly());
        expenseFromRepo.setDateUpdated(expenseUpdateRequest.getMonth());


        UserUpdateIncomeAndFundRequest userUpdateIncomeAndFundRequest = new UserUpdateIncomeAndFundRequest()
                .setAvailableFund(userFromRepo.getAvailableFund())
                .setMonthlyIncome(userFromRepo.getMonthlyIncome())
                .setUsername(userFromRepo.getUsername());

        return (expenseRepository.updateExpense(expenseFromRepo)
                && userRepository.updateIncomeAndFund(userUpdateIncomeAndFundRequest));
    }

    @Override
    public boolean allocateExpense(ExpenseAllocateRequest expenseAllocateRequest) {
        Expense expenseFromRepo = expenseRepository.getExpense(expenseAllocateRequest.getUsername(),
                expenseAllocateRequest.getExpenseId());

        User userFromRepo = userRepository.getUserByUsername(expenseAllocateRequest.getUsername());

        if (expenseFromRepo == null) {
            return false;
        }

        // Allocated amount > remaining
        if (expenseAllocateRequest.getAllocated().setScale(2, RoundingMode.HALF_UP)
                .compareTo(expenseFromRepo.getRemaining()) > 0) {
            expenseAllocateRequest.setAllocated((expenseFromRepo.getRemaining()));
        }

        // Allocated amount > user available fund
        if (expenseAllocateRequest.getAllocated().setScale(2, RoundingMode.HALF_UP)
                .compareTo(userFromRepo.getAvailableFund()) > 0) {
            expenseAllocateRequest.setAllocated((userFromRepo.getAvailableFund()));
        }

        expenseFromRepo.setAllocated(expenseFromRepo.getAllocated()
                .add(expenseAllocateRequest.getAllocated()));

        expenseFromRepo.setRemaining(expenseFromRepo.getRemaining()
                .subtract(expenseAllocateRequest.getAllocated()));

        expenseRepository.updateExpense(expenseFromRepo);

        // update user fund
        userFromRepo.setAvailableFund(userFromRepo.getAvailableFund()
                .subtract(expenseAllocateRequest.getAllocated()));

        UserUpdateIncomeAndFundRequest userUpdateIncomeAndFundRequest = new UserUpdateIncomeAndFundRequest()
                .setAvailableFund(userFromRepo.getAvailableFund())
                .setMonthlyIncome(userFromRepo.getMonthlyIncome())
                .setUsername(userFromRepo.getUsername());

        return (expenseRepository.updateExpense(expenseFromRepo)
                && userRepository.updateIncomeAndFund(userUpdateIncomeAndFundRequest));
    }

    @Override
    public boolean deleteExpense(ExpenseDeleteRequest expenseDeleteRequest) {
        return expenseRepository.deleteExpense(expenseDeleteRequest.getExpenseId());
    }


}
