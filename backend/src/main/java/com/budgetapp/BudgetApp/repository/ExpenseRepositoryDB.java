package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.model.Expense;
import com.budgetapp.BudgetApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        final String GET_EXPENSES = "SELECT * FROM Expense WHERE `username` = ?;";
        return jdbc.query(GET_EXPENSES, new ExpenseMapper(), username);
    }

    public static final class ExpenseMapper implements RowMapper<Expense> {
        @Override
        public Expense mapRow(ResultSet resultSet, int i) throws SQLException {
            Expense expense = new Expense();
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
