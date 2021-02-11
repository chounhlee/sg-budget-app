package com.budgetapp.BudgetApp.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private String username;
    private String userPassword;
    private BigDecimal monthlyIncome;
    private BigDecimal availableFund;
}
