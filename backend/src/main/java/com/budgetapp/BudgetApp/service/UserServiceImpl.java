package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.UserRegisterRequest;
import com.budgetapp.BudgetApp.controller.request.UserUpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.controller.request.UserLoginRequest;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        try {
            User user = userRepository.getUserByUsername(userLoginRequest.getUsername());

            if (!user.getUserPassword().equals(userLoginRequest.getPassword()) || user.getUserPassword().isEmpty()) {
                return null;
            }

            return user;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public User register(UserRegisterRequest userRegisterRequest) {
        User user = new User()
                .setAvailableFund(userRegisterRequest.getAvailableFund())
                .setMonthlyIncome(userRegisterRequest.getMonthlyIncome())
                .setUsername(userRegisterRequest.getUsername())
                .setUserPassword(userRegisterRequest.getUserPassword());
        
        return userRepository.createUser(user);
    }

    @Override
    public UserIncomeAndExpenseDto getIncomeAndFund(String username) {
        User user = userRepository.getIncomeAndFund(username);

        if (user == null) {
            return null;
        } else {
            UserIncomeAndExpenseDto userIncomeAndExpenseDto = new UserIncomeAndExpenseDto();
            userIncomeAndExpenseDto.setMonthlyIncome(user.getMonthlyIncome());
            userIncomeAndExpenseDto.setAvailableFund(user.getAvailableFund());
            return userIncomeAndExpenseDto;
        }
    }

    @Override
    public boolean updateIncomeAndFund(UserUpdateIncomeAndFundRequest userUpdateIncomeAndFundRequest) {
        return userRepository.updateIncomeAndFund(userUpdateIncomeAndFundRequest);
    }
}
