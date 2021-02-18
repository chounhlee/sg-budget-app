package com.budgetapp.BudgetApp.controller;

import com.budgetapp.BudgetApp.controller.request.*;
import com.budgetapp.BudgetApp.dto.UserIncomeAndExpenseDto;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.service.BudgetService;
import com.budgetapp.BudgetApp.service.UserService;
import org.springframework.http.HttpHeaders;
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

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        User user = userService.register(userRegisterRequest);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiMessage().setMessage("Username already taken"));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiMessage().setMessage(user.getUsername() + " created"));
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiMessage().setMessage("Invalid username or password"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie","username="+user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).headers(headers)
                .body(new ApiMessage().setMessage("Login success"));
    }

    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<Object> loginUser(@RequestBody UserLogoutRequest userLogoutRequest) {


        if (userLogoutRequest.getUsername().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiMessage().setMessage("Invalid username or password"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie","username=");
        return ResponseEntity.status(HttpStatus.OK).headers(headers)
                .body(new ApiMessage().setMessage("Logout success"));
    }

    @CrossOrigin
    @GetMapping("/income")
    public ResponseEntity<Object> getIncomeAndFund(@RequestParam String username) {
        UserIncomeAndExpenseDto userIncomeAndExpenseDto =
                userService.getIncomeAndFund(username);

        if (userIncomeAndExpenseDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiMessage().setMessage("Invalid Request"));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(userIncomeAndExpenseDto);
    }

    @CrossOrigin
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
