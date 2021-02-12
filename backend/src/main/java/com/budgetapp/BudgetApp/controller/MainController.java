package com.budgetapp.BudgetApp.controller;

import com.budgetapp.BudgetApp.controller.request.*;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.service.BudgetService;
import com.budgetapp.BudgetApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    final
    BudgetService budgetService;

    final
    UserService userService;

    private HttpStatus status;
    private Message message;

    public MainController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
        this.message = new Message();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User userRawData) {
        User user = userService.register(userRawData);

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
        // Ignore month for now
        List<Expense> expenses = budgetService.getExpenses(userRawData.getUsername());

        if (expenses == null) {
            status = HttpStatus.BAD_REQUEST;
            message.setMessage("Invalid Request");
            return new ResponseEntity<>(message, status);
        } else {
            // Create imaginary cookie
            status = HttpStatus.OK;
            message.setMessage("Login success");
            return new ResponseEntity<>(expenses, status);
        }
    }

    @PostMapping("/expenses")
    public ResponseEntity<Object> addExpense(@RequestBody AddExpenseRequest addExpenseRequest) {
        Expense expense = budgetService.addExpense(addExpenseRequest);

        if (expense == null) {
            status = HttpStatus.BAD_REQUEST;
            message.setMessage("Invalid Request");
        } else {
            // Create imaginary cookie
            status = HttpStatus.CREATED;
            message.setMessage("Expense created successfully");
        }

        return new ResponseEntity<>(message, status);
    }

    @PutMapping("/expenses")
    public ResponseEntity<Object> updateExpense(@RequestBody UpdateExpenseRequest updateExpenseRequest) {
        if (!budgetService.updateExpense(updateExpenseRequest)) {
            status = HttpStatus.BAD_REQUEST;
            message.setMessage("Invalid Request");
        } else {
            status = HttpStatus.OK;
            message.setMessage("Expense updated successfully");
        }

        return new ResponseEntity<>(message, status);
    }

    @DeleteMapping("/expenses")
    public ResponseEntity<Object> deleteExpense(@RequestBody DeleteExpenseRequest deleteExpenseRequest) {
        if (!budgetService.deleteExpense(deleteExpenseRequest)) {
            status = HttpStatus.BAD_REQUEST;
            message.setMessage("Invalid Request");
        } else {
            status = HttpStatus.OK;
            message.setMessage("Expense delete successfully");
        }

        return new ResponseEntity<>(message, status);
    }

    @GetMapping("/income")
    public ResponseEntity<Object> getIncomeAndFund(@RequestBody GetUserIncomeRequest getUserIncomeRequest) {
        UserIncomeAndExpenseDto userIncomeAndExpenseDto =
                userService.getIncomeAndFund(getUserIncomeRequest.getUsername());

        if (userIncomeAndExpenseDto == null) {
            status = HttpStatus.BAD_REQUEST;
            message.setMessage("Invalid Request");
            return new ResponseEntity<>(message, status);
        } else {
            status = HttpStatus.OK;
            return new ResponseEntity<>(userIncomeAndExpenseDto, status);
        }
    }


    @PutMapping("/income")
    public ResponseEntity<Object> updateIncomeAndFund(@RequestBody UpdateIncomeAndFundRequest updateIncomeAndFundRequest) {
        if (!userService.updateIncomeAndFund(updateIncomeAndFundRequest)) {
            status = HttpStatus.BAD_REQUEST;
            message.setMessage("Invalid Request");
        } else {
            status = HttpStatus.OK;
            message.setMessage("Income and Fund updated successfully");
        }

        return new ResponseEntity<>(message, status);
    }
}
