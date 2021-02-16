package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserRegisterRequest {
    private String username;
    private String userPassword;
    private BigDecimal monthlyIncome;
    private BigDecimal availableFund;
}
