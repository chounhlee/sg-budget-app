package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.UpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.User;

public interface UserService {
    User login(String username, String userPassword);
    User register(User user);
    UserIncomeAndExpenseDto getIncomeAndFund(String username);
    boolean updateIncomeAndFund(UpdateIncomeAndFundRequest updateIncomeAndFundRequest);
}
