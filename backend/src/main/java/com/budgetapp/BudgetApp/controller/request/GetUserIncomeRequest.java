package com.budgetapp.BudgetApp.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetUserIncomeRequest {
    private String username;
    private LocalDate month;
}
