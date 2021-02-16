package com.budgetapp.BudgetApp.controller;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiMessage {
    private String message;
}
