package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.model.User;

public interface UserService {
    User login(String username, String userPassword);
    User register(User user);
}
