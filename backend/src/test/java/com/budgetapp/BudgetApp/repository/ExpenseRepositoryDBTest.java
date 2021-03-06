package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(value = {"/Test_DB_DROP_TABLES.sql"})
public class ExpenseRepositoryDBTest {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        setupUserAndExpenseTable();
    }

    private void setupUserAndExpenseTable() {
        User user = new User()
                .setUsername("user1")
                .setUserPassword("1234")
                .setMonthlyIncome(new BigDecimal(5000))
                .setAvailableFund(new BigDecimal(6000));
        userRepository.createUser(user);

        Expense expense = new Expense()
                .setUsername(userRepository.getUserByUsername("user1").getUsername())
                .setExpenseName("Rent")
                .setAmount(new BigDecimal(500))
                .setDateUpdated(LocalDate.now())
                .setRemaining(new BigDecimal(500))
                .setAllocated(BigDecimal.ZERO)
                .setMonthly(true);

        expenseRepository.addExpense(expense);
    }

    @Test
    public void testUpdateExpense() {
        Expense expenseFromRepo = expenseRepository.getExpense("user1", 1);

        expenseFromRepo.setRemaining(new BigDecimal(2200));
        expenseRepository.updateExpense(expenseFromRepo);

        expenseFromRepo = expenseRepository.getExpense("user1", 1);
        assertEquals(new BigDecimal(2200).setScale(2, RoundingMode.HALF_UP),
                expenseFromRepo.getRemaining());
    }


}