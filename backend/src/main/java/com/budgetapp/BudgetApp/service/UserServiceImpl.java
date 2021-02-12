package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.UpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.controller.request.UserLoginRequest;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userRepository.getUserByUsername(userLoginRequest.getUsername());

        if (!user.getUserPassword().equals(userLoginRequest.getPassword())) {
            return null;
        }

        return user;
    }

    @Override
    public User register(User user) {
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
    public boolean updateIncomeAndFund(UpdateIncomeAndFundRequest updateIncomeAndFundRequest) {
        return userRepository.updateIncomeAndFund(updateIncomeAndFundRequest);
    }
}
