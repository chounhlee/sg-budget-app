package com.budgetapp.BudgetApp.controller;

import com.budgetapp.BudgetApp.controller.request.*;
import com.budgetapp.BudgetApp.dto.ExpenseDto;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.service.BudgetService;
import com.budgetapp.BudgetApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        // Ignore month for now
        List<Expense> expenses = budgetService.getExpenses(userRawData.getUsername());

        HttpStatus status;
        Message message = new Message();

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
        Expense expense = new Expense();
        expense.setUsername(addExpenseRequest.getUsername());
        expense.setAmount(addExpenseRequest.getAmount());
        expense.setExpenseName(addExpenseRequest.getExpenseName());
        expense.setRemaining(addExpenseRequest.getAmount());
        expense.setMonthly(addExpenseRequest.isMonthly());
        expense.setAllocated(BigDecimal.ZERO);
        expense.setDateUpdated(addExpenseRequest.getMonth());

        expense = budgetService.createExpense(expense);

        HttpStatus status;
        Message message = new Message();

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
        Expense expense = new Expense();
        expense.setId(updateExpenseRequest.getExpenseId());
        expense.setUsername(updateExpenseRequest.getUsername());
        expense.setAmount(updateExpenseRequest.getAmount());
        expense.setExpenseName(updateExpenseRequest.getExpenseName());
        expense.setMonthly(updateExpenseRequest.isMonthly());
        expense.setAllocated(updateExpenseRequest.getAllocated());
        expense.setDateUpdated(updateExpenseRequest.getMonth());

        expense = budgetService.updateExpense(expense);

        HttpStatus status;
        Message message = new Message();

        if (expense == null) {
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
        HttpStatus status;
        Message message = new Message();

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
        HttpStatus status;
        Message message = new Message();
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
        HttpStatus status;
        Message message = new Message();

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
