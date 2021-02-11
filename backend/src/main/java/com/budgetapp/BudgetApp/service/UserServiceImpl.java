package com.budgetapp.BudgetApp.service;

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
    public User login(String userName, String userPassword) {
        User user = userRepository.getUserByUsername(userName);
        if (!user.getUserPassword().equals(userPassword)) {
            return null;
        }

        return user;
    }

    @Override
    public User register(User user) {
        return userRepository.createUser(user);
    }
}
