package com.budgetapp.BudgetApp.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class User {
    private String username;
    private String userPassword;
    private BigDecimal monthlyIncome;
    private BigDecimal availableFund;
}
