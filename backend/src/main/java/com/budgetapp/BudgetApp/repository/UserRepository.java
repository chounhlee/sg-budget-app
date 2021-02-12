package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.controller.request.UpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.model.User;

public interface UserRepository {
    User getUserByUsername(String username);
    User createUser(User user);
    User getIncomeAndFund(String username);
    boolean updateIncomeAndFund(UpdateIncomeAndFundRequest updateIncomeAndFundRequest);
}
