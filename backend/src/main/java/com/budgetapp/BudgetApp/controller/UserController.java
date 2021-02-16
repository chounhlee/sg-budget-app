package com.budgetapp.BudgetApp.controller;

import com.budgetapp.BudgetApp.controller.request.UserGetRequest;
import com.budgetapp.BudgetApp.controller.request.UserUpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.controller.request.UserLoginRequest;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.service.BudgetService;
import com.budgetapp.BudgetApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    final
    BudgetService budgetService;

    final
    UserService userService;

    public UserController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User userRawData) {
        User user = userService.register(userRawData);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiMessage().setMessage("Username already taken"));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiMessage().setMessage(user.getUsername() + " created"));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiMessage().setMessage("Invalid username or password"));
        }

        // Create imaginary cookie
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiMessage().setMessage("Login success"));
    }

    @GetMapping("/income")
    public ResponseEntity<Object> getIncomeAndFund(@RequestBody UserGetRequest userGetRequest) {
        UserIncomeAndExpenseDto userIncomeAndExpenseDto =
                userService.getIncomeAndFund(userGetRequest.getUsername());

        if (userIncomeAndExpenseDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiMessage().setMessage("Invalid Request"));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(userIncomeAndExpenseDto);
    }


    @PutMapping("/income")
    public ResponseEntity<Object> updateIncomeAndFund(@RequestBody UserUpdateIncomeAndFundRequest userUpdateIncomeAndFundRequest) {
        if (!userService.updateIncomeAndFund(userUpdateIncomeAndFundRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiMessage().setMessage("Invalid Request"));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiMessage().setMessage("Income and Fund updated successfully"));
    }
}
