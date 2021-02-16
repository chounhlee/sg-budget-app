package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.UserUpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.controller.request.UserLoginRequest;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.User;

public interface UserService {
    User login(UserLoginRequest userLoginRequest);
    User register(User user);
    UserIncomeAndExpenseDto getIncomeAndFund(String username);
    boolean updateIncomeAndFund(UserUpdateIncomeAndFundRequest userUpdateIncomeAndFundRequest);
}
