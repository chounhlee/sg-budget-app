package com.budgetapp.BudgetApp.controller;

import com.budgetapp.BudgetApp.controller.request.*;
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
    private final BudgetService budgetService;
    private final UserService userService;

    public MainController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @GetMapping("/expenses")
    public ResponseEntity<Object> getExpenses(@RequestBody User userRawData) {
        // Use imaginary cookie (token, username)
        // Ignore month for now
        List<Expense> expenses = budgetService.getExpenses(userRawData.getUsername());

        if (expenses == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiMessage().setMessage("Invalid Request"));
        }

        // Create imaginary cookie
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiMessage().setMessage("Login success"));
    }

    @PostMapping("/expenses")
    public ResponseEntity<Object> addExpense(@RequestBody ExpenseAddRequest addExpenseRequest) {
        Expense expense = budgetService.addExpense(addExpenseRequest);

        if (expense == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiMessage().setMessage("Invalid Request"));
        }

        // Create imaginary cookie
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiMessage().setMessage("Expense created successfully"));
    }

    @PutMapping("/expenses")
    public ResponseEntity<Object> updateExpense(@RequestBody ExpenseUpdateRequest expenseUpdateRequest) {
        if (!budgetService.updateExpense(expenseUpdateRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiMessage().setMessage("Invalid Request"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiMessage().setMessage("Expense updated successfully"));

    }

    @DeleteMapping("/expenses")
    public ResponseEntity<Object> deleteExpense(@RequestBody ExpenseDeleteRequest expenseDeleteRequest) {
        if (!budgetService.deleteExpense(expenseDeleteRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiMessage().setMessage("Invalid Request"));

        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiMessage().setMessage("Expense delete successfully"));
    }
}
