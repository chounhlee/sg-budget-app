package com.budgetapp.BudgetApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Expense {
    private int id;
    private String username;
    private String expenseName;

    @JsonProperty("isMonthly")
    private boolean isMonthly;

    private BigDecimal amount;
    private BigDecimal allocated;
    private BigDecimal remaining;
    private LocalDate dateUpdated;
}
