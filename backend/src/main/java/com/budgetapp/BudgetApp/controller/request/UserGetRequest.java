package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserGetRequest {
    private String username;
    private LocalDate month;
}
