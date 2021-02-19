package com.budgetapp.BudgetApp.repository;

import com.budgetapp.BudgetApp.controller.request.UserUpdateIncomeAndFundRequest;
import com.budgetapp.BudgetApp.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryDB implements UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepositoryDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            final String GET_USER_BY_USERNAME =
                    "SELECT * FROM `user` " +
                            " WHERE username = ?;";

            return jdbc.queryForObject(GET_USER_BY_USERNAME, new UserMapper(), username);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    @Transactional
    public User createUser(User user) {
        try {
            final String INSERT_USER =
                    "INSERT INTO `user` (`username`, `userPassword`, `monthlyIncome`, `availableFund`)" +
                            " VALUES (?, ?, ?, ?);";
            jdbc.update(INSERT_USER,
                    user.getUsername(),
                    user.getUserPassword(),
                    user.getMonthlyIncome(),
                    user.getAvailableFund());

            return user;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getIncomeAndFund(String username) {
        try {
            final String GET_INCOME_AND_FUND_BY_USERNAME =
                    "SELECT * FROM `User` WHERE `username` = ?;";

            return jdbc.queryForObject(GET_INCOME_AND_FUND_BY_USERNAME, new UserMapper(), username);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean updateIncomeAndFund(UserUpdateIncomeAndFundRequest userUpdateIncomeAndFundRequest) {
        try {
            final String UPDATE_INCOME_AND_FUND_BY_USERNAME =
                    " UPDATE `user` SET " +
                            " `availableFund` = ?, " +
                            " `monthlyIncome` = ? " +
                            " WHERE (`username` = ?);";

            jdbc.update(UPDATE_INCOME_AND_FUND_BY_USERNAME,
                    userUpdateIncomeAndFundRequest.getAvailableFund(),
                    userUpdateIncomeAndFundRequest.getMonthlyIncome(),
                    userUpdateIncomeAndFundRequest.getUsername());
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }


    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUsername(resultSet.getString("username"));
            user.setUserPassword(resultSet.getString("userPassword"));
            user.setAvailableFund(resultSet.getBigDecimal("availableFund")
                    .setScale(2, RoundingMode.HALF_UP));
            user.setMonthlyIncome(resultSet.getBigDecimal("monthlyIncome")
                    .setScale(2, RoundingMode.HALF_UP));

            return user;
        }
    }
}
