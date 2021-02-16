package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.controller.request.UserUpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(value = {"/Test_DB_DROP_TABLES.sql"})
class UserRepositoryDBTest {
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
    }

    @Test
    public void testUpdateIncomeAndFund() {
        User userFromRepo = userRepository.getUserByUsername("user1");

        UserUpdateIncomeAndFundRequest userUpdateIncomeAndFundRequest = new UserUpdateIncomeAndFundRequest();
        userUpdateIncomeAndFundRequest.setUsername("user1");
        userUpdateIncomeAndFundRequest.setMonthlyIncome(new BigDecimal(2000));
        userUpdateIncomeAndFundRequest.setAvailableFund(new BigDecimal(1000));

        userRepository.updateIncomeAndFund(userUpdateIncomeAndFundRequest);

        userFromRepo = userRepository.getUserByUsername("user1");
        assertEquals(new BigDecimal(2000).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getMonthlyIncome());

        assertEquals(new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getAvailableFund());
    }
}