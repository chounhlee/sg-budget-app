package com.budgetapp.BudgetApp.controller;

import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.service.BudgetService;
import com.budgetapp.BudgetApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    final
    BudgetService budgetService;

    final
    UserService userService;

    public MainController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User userRawData) {
        User user = userService.register(userRawData);

        HttpStatus status;
        Message message = new Message();

        if (user == null) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
            message.setMessage("Username already taken");
        } else {
            status = HttpStatus.CREATED;
            message.setMessage(user.getUsername() + " created");
        }

        return new ResponseEntity<>(message, status);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User userRawData) {
        User user = userService.login(userRawData.getUsername(), userRawData.getUserPassword());

        HttpStatus status;
        Message message = new Message();

        if (user == null) {
            status = HttpStatus.UNAUTHORIZED;
            message.setMessage("Invalid username or password");
        } else {
            // Create imaginary cookie
            status = HttpStatus.OK;
            message.setMessage("Login success");
        }

        return new ResponseEntity<>(message, status);
    }

    @GetMapping("/expenses")
    public ResponseEntity<Object> getExpenses(@RequestBody User userRawData) {
        // Use imaginary cookie (token, username)
        List<Expense> expenses = budgetService.getExpenses(userRawData.getUsername());

        HttpStatus status;
        Message message = new Message();

        if (expenses == null) {
            status = HttpStatus.BAD_REQUEST;
            message.setMessage("Invalid Request");
        } else {
            // Create imaginary cookie
            status = HttpStatus.OK;
            message.setMessage("Login success");
        }

        return new ResponseEntity<>(message, status);
    }
}
