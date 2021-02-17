package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExpenseRepositoryDB implements ExpenseRepository {

    final
    JdbcTemplate jdbc;

    public ExpenseRepositoryDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Expense> getExpenses(String username) {
        final String GET_EXPENSES = "SELECT * FROM `Expense` WHERE `username` = ?;";
        List<Expense> expenses = jdbc.query(GET_EXPENSES, new ExpenseMapper(), username);
        final String test = "foo";
        return expenses;
    }

    @Override
    @Transactional
    public Expense addExpense(Expense expense) {
        try {
            final String ADD_EXPENSE =
                    "INSERT INTO `expense` " +
                            " (`username`, `expenseName`, `isMonthly`, `amount`, `dateUpdated`, `allocated`, `remaining`) " +
                            " VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?);";

            jdbc.update(ADD_EXPENSE, expense.getUsername(), expense.getExpenseName(), expense.isMonthly(),
                    expense.getAmount(), expense.getDateUpdated(),
                    expense.getAllocated(), expense.getRemaining());

            int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID();", Integer.class);
            expense.setId(newId);

            return expense;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean updateExpense(Expense expense) {
        try {
            final String UPDATE_EXPENSE_BY_ID_AND_USERNAME =
                    "UPDATE `expense` SET " +
                            " `expenseName` = ?, " +
                            " `amount` = ?, " +
                            " `allocated` = ?, " +
                            " `remaining` = ?, " +
                            " `isMonthly` = ?" +
                            " WHERE (`id` = ?) AND (`username` = ?);";

            jdbc.update(UPDATE_EXPENSE_BY_ID_AND_USERNAME,
                    expense.getExpenseName(),
                    expense.getAmount(),
                    expense.getAllocated(),
                    expense.getRemaining(),
                    expense.isMonthly(),
                    expense.getId(),
                    expense.getUsername());
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteExpense(int id) {
        // Also fix fund here

        try {
            final String GET_EXPENSE_BY_ID = "SELECT * FROM `Expense` WHERE `id` = ?;";
            jdbc.queryForObject(GET_EXPENSE_BY_ID, new ExpenseMapper(), id);

            final String DELETE_EXPENSE_BY_ID = "DELETE FROM `expense` WHERE `id` = ?;";
            jdbc.update(DELETE_EXPENSE_BY_ID, id);

            return true;
        } catch (DataAccessException ex) {
            return false;
        }

    }

    @Override
    public Expense getExpense(String username, int id) {
        try {
            final String GET_EXPENSE_BY_ID = "SELECT * FROM `Expense` WHERE `username` = ? AND `id` = ?;";
            return jdbc.queryForObject(GET_EXPENSE_BY_ID, new ExpenseMapper(), username, id);
        } catch (DataAccessException ex) {
            return null;
        }
    }


    public static final class ExpenseMapper implements RowMapper<Expense> {
        @Override
        public Expense mapRow(ResultSet resultSet, int i) throws SQLException {
            Expense expense = new Expense();
            expense.setId(resultSet.getInt("id"));
            expense.setUsername(resultSet.getString("username"));
            expense.setExpenseName(resultSet.getString("expenseName"));
            expense.setMonthly(resultSet.getBoolean("isMonthly"));
            expense.setAmount(resultSet.getBigDecimal("amount")
                    .setScale(2, RoundingMode.HALF_UP));
            expense.setAllocated(resultSet.getBigDecimal("allocated")
                    .setScale(2, RoundingMode.HALF_UP));
            expense.setRemaining(resultSet.getBigDecimal("remaining")
                    .setScale(2, RoundingMode.HALF_UP));
            expense.setDateUpdated(resultSet.getDate("dateUpdated").toLocalDate());

            return expense;
        }
    }
}
