package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class UserRegisterRequest {
    private String username;
    private String userPassword;
    private BigDecimal monthlyIncome;
    private BigDecimal availableFund;
}
