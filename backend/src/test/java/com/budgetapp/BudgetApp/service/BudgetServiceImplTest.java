package com.budgetapp.BudgetApp.service;

import com.budgetapp.BudgetApp.controller.request.*;
import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import com.budgetapp.BudgetApp.repository.ExpenseRepository;
import com.budgetapp.BudgetApp.repository.UserRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(value = {"/Test_DB_DROP_TABLES.sql"})
public class BudgetServiceImplTest {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserService userService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        // Every single test will only have
        //  1 user with username: "user1" and
        //  1 expense with id: 1
        setupUserAndExpenseTable();
    }

    private void setupUserAndExpenseTable() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest()
                .setUsername("user1")
                .setUserPassword("1234")
                .setMonthlyIncome(new BigDecimal(5000))
                .setAvailableFund(new BigDecimal(6000));

        userService.register(userRegisterRequest);

        ExpenseAddRequest expenseAddRequest = new ExpenseAddRequest()
                .setUsername(userRepository.getUserByUsername("user1").getUsername())
                .setExpenseName("Rent")
                .setAmount(new BigDecimal(500))
                .setMonthly(true)
                .setMonth(LocalDate.now());

        budgetService.addExpense(expenseAddRequest);
    }

    @Test
    public void testAddGetExpenses() {
        User user = userRepository.getUserByUsername("user1");

        ExpenseAddRequest expenseAddRequest = new ExpenseAddRequest()
                .setUsername(user.getUsername())
                .setExpenseName("Transportation")
                .setAmount(new BigDecimal(200))
                .setMonth(LocalDate.now())
                .setMonthly(false);

        budgetService.addExpense(expenseAddRequest);

        List<Expense> expensesFromRepo = budgetService.getExpenses(user.getUsername());
        assertEquals(2, expensesFromRepo.size());
    }

    @Test
    public void testAddGetExpenses2() {
        User user = userRepository.getUserByUsername("user1");
        ExpenseAddRequest expenseAddRequest = new ExpenseAddRequest()
                .setUsername(user.getUsername())
                .setExpenseName("Utility")
                .setAmount(new BigDecimal(200))
                .setMonth(LocalDate.now())
                .setMonthly(false);
        budgetService.addExpense(expenseAddRequest);

        List<Expense> expensesFromRepo = budgetService.getExpenses(user.getUsername());
        assertEquals(2, expensesFromRepo.size());
    }

    @Test
    public void testAllocateToExpense1() {
        // When user has enough available fund
        // User allocate greater amount than remaining expense amount
        User user = userRepository.getUserByUsername("user1");

        ExpenseAllocateRequest expenseAllocateRequest = new ExpenseAllocateRequest()
                .setExpenseId(1)
                .setAllocated(new BigDecimal(2000))
                .setUsername(user.getUsername())
                .setMonth(LocalDate.now());

        budgetService.allocateExpense(expenseAllocateRequest);

        List<Expense> expensesFromRepo = budgetService.getExpenses(user.getUsername());
        Expense rentExpense = expensesFromRepo.get(0);
        User userFromRepo = userRepository.getUserByUsername("user1");

        assertEquals(new BigDecimal(0).setScale(2, RoundingMode.HALF_UP),
                rentExpense.getRemaining());

        assertEquals(new BigDecimal(5500).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getAvailableFund());
    }

    @Test
    public void testAllocateToExpense2() {
        // When allocate amount is greater than user available fund
        // Then it should only use available fund to allocate
        User user = userRepository.getUserByUsername("user1");

        ExpenseAddRequest expenseAddRequest = new ExpenseAddRequest()
                .setUsername(userRepository.getUserByUsername("user1").getUsername())
                .setExpenseName("Lambo")
                .setAmount(new BigDecimal(10000))
                .setMonthly(true)
                .setMonth(LocalDate.now());
        budgetService.addExpense(expenseAddRequest);

        ExpenseAllocateRequest expenseAllocateRequest = new ExpenseAllocateRequest()
                .setExpenseId(2)
                .setAllocated(new BigDecimal(10000))
                .setUsername(user.getUsername())
                .setMonth(LocalDate.now());

        budgetService.allocateExpense(expenseAllocateRequest);

        List<Expense> expensesFromRepo = budgetService.getExpenses(user.getUsername());
        Expense lamboExpense = expensesFromRepo.get(1);
        User userFromRepo = userRepository.getUserByUsername("user1");

        assertEquals(new BigDecimal(4000).setScale(2, RoundingMode.HALF_UP),
                lamboExpense.getRemaining());

        assertEquals(new BigDecimal(0).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getAvailableFund());
    }

    @Test
    public void testAllocateToExpense3() {
        // User allocate nothing
        User user = userRepository.getUserByUsername("user1");

        ExpenseAllocateRequest expenseAllocateRequest = new ExpenseAllocateRequest()
                .setExpenseId(1)
                .setAllocated(new BigDecimal(0))
                .setUsername(user.getUsername())
                .setMonth(LocalDate.now());
        budgetService.allocateExpense(expenseAllocateRequest);

        List<Expense> expensesFromRepo = budgetService.getExpenses(user.getUsername());
        Expense rentExpense = expensesFromRepo.get(0);
        User userFromRepo = userRepository.getUserByUsername("user1");

        assertEquals(new BigDecimal(500).setScale(2, RoundingMode.HALF_UP),
                rentExpense.getRemaining());

        assertEquals(new BigDecimal(6000).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getAvailableFund());
    }

    @Test
    public void testAllocateToExpense4() {
        // When user has enough available fund
        // User allocate some amount
        User user = userRepository.getUserByUsername("user1");

        ExpenseAddRequest expenseAddRequest = new ExpenseAddRequest()
                .setUsername(userRepository.getUserByUsername("user1").getUsername())
                .setExpenseName("Lambo")
                .setAmount(new BigDecimal(10000))
                .setMonthly(true)
                .setMonth(LocalDate.now());
        budgetService.addExpense(expenseAddRequest);

        ExpenseAllocateRequest expenseAllocateRequest = new ExpenseAllocateRequest()
                .setExpenseId(2)
                .setAllocated(new BigDecimal(5000))
                .setUsername(user.getUsername())
                .setMonth(LocalDate.now());
        budgetService.allocateExpense(expenseAllocateRequest);

        List<Expense> expensesFromRepo = budgetService.getExpenses(user.getUsername());
        Expense lamboExpense = expensesFromRepo.get(1);
        User userFromRepo = userRepository.getUserByUsername("user1");

        assertEquals(new BigDecimal(5000).setScale(2, RoundingMode.HALF_UP),
                lamboExpense.getRemaining());

        assertEquals(new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getAvailableFund());
    }

    @Test
    public void testEditExpense1() {
        // Test with allocated = 0
        User userFromRepo = userRepository.getUserByUsername("user1");

        ExpenseAllocateRequest expenseAllocateRequest = new ExpenseAllocateRequest()
                .setExpenseId(1)
                .setAllocated(new BigDecimal(200))
                .setUsername(userFromRepo.getUsername())
                .setMonth(LocalDate.now());
        budgetService.allocateExpense(expenseAllocateRequest);

        ExpenseUpdateRequest expenseUpdateRequest = new ExpenseUpdateRequest()
                .setExpenseId(1)
                .setUsername(userFromRepo.getUsername())
                .setMonth(LocalDate.now())
                .setExpenseName("New Rent")
                .setAmount(new BigDecimal(1000));
        budgetService.updateExpense(expenseUpdateRequest);

        Expense expenseFromRepo = expenseRepository.getExpense("user1",
                expenseUpdateRequest.getExpenseId());

        assertEquals(new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),
                expenseFromRepo.getAmount());

        assertEquals("New Rent",
                expenseFromRepo.getExpenseName());

        assertEquals(new BigDecimal(6000).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getAvailableFund());

    }

    @Test
    public void testEditExpense2() {
        // Test with allocated that has value
        User userFromRepo = userRepository.getUserByUsername("user1");

        ExpenseUpdateRequest expenseUpdateRequest = new ExpenseUpdateRequest()
                .setExpenseId(1)
                .setUsername(userFromRepo.getUsername())
                .setMonth(LocalDate.now())
                .setExpenseName("New Rent")
                .setAmount(new BigDecimal(1000));

        budgetService.updateExpense(expenseUpdateRequest);

        Expense expenseFromRepo = expenseRepository.getExpense("user1",
                expenseUpdateRequest.getExpenseId());

        assertEquals(new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP),
                expenseFromRepo.getAmount());

        assertEquals("New Rent",
                expenseFromRepo.getExpenseName());

        assertEquals(new BigDecimal(6000).setScale(2, RoundingMode.HALF_UP),
                userFromRepo.getAvailableFund());

    }

    @Test
    public void testDeleteExpenseWithAllocated() {
//
//        assertTrue(expenseRepository.deleteExpense(1));
//
//        assertFalse(expenseRepository.deleteExpense(1));
//        assertEquals(new BigDecimal(6000).setScale(2, RoundingMode.HALF_UP),
//                userRepository.getIncomeAndFund("user1").getAvailableFund());
    }

    @Test
    public void testDeleteExpenseWithoutAllocated() {
        ExpenseDeleteRequest expenseDeleteRequest = new ExpenseDeleteRequest()
                .setUsername("user1")
                .setMonth(LocalDate.now())
                .setExpenseId(1);

        assertTrue(budgetService.deleteExpense(expenseDeleteRequest));


        assertFalse(budgetService.deleteExpense(expenseDeleteRequest));

        assertEquals(new BigDecimal(6000).setScale(2, RoundingMode.HALF_UP),
                userRepository.getIncomeAndFund("user1").getAvailableFund());
    }
}