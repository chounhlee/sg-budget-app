use budgetdbv2;

--  Create 2 users
INSERT INTO `budgetdbv2`.`user` (`username`, `userPassword`, `monthlyIncome`) VALUES ('user1', 'password', '2000');
INSERT INTO `budgetdbv2`.`user` (`username`, `userPassword`, `monthlyIncome`) VALUES ('user2', 'password2', '3000');

--  Create period for user 1
INSERT INTO `budgetdbv2`.`period` ( `periodDate`, `availableFund`, `username`) VALUES ( '2020-02-01', '1000', 'user1');
INSERT INTO `budgetdbv2`.`period` (`periodDate`, `availableFund`, `username`) VALUES ( '2020-03-02', '3000', 'user1');
INSERT INTO `budgetdbv2`.`period` (`periodDate`, `availableFund`, `username`) VALUES ('2020-04-02', '5000', 'user1');
INSERT INTO `budgetdbv2`.`period` ( `periodDate`, `availableFund`, `username`) VALUES ('2020-05-02', '7000', 'user1');
INSERT INTO `budgetdbv2`.`period` (`periodDate`, `availableFund`, `username`) VALUES ('2020-06-02', '9000', 'user1');
INSERT INTO `budgetdbv2`.`period` ( `periodDate`, `availableFund`, `username`) VALUES ('2020-07-02', '11000', 'user1');
INSERT INTO `budgetdbv2`.`period` ( `periodDate`, `availableFund`, `username`) VALUES ( '2020-08-02', '13000', 'user1');
INSERT INTO `budgetdbv2`.`period` (`periodDate`, `availableFund`, `username`) VALUES ('2020-09-02', '15000', 'user1');
INSERT INTO `budgetdbv2`.`period` ( `periodDate`, `availableFund`, `username`) VALUES ( '2020-10-02', '17000', 'user1');
INSERT INTO `budgetdbv2`.`period` ( `periodDate`, `availableFund`, `username`) VALUES ( '2020-11-02', '19000', 'user1');
INSERT INTO `budgetdbv2`.`period` ( `periodDate`, `availableFund`, `username`) VALUES ( '2020-12-02', '21000', 'user1');

--  Create 2 expense, one for recurring, and one for one-time
INSERT INTO `budgetdbv2`.`expense` (`expenseName`, `isMonthly`, `amount`, `dateCreated`) VALUES ('Rent', '1', '500', '2020-02-01');
INSERT INTO `budgetdbv2`.`expense` (`expenseName`, `isMonthly`, `amount`, `dateCreated`) VALUES ('Spine Surgery', '0', '2000', '2020-02-01');

--  Create period_expense table
INSERT INTO `budgetdbv2`.`period_expense` (`periodId`, `expenseId`, `allocated`, `remaining`) VALUES ('1', '1', '500', '0');
INSERT INTO `budgetdbv2`.`period_expense` (`periodId`, `expenseId`, `allocated`, `remaining`) VALUES ('1', '2', '0', '2000');

--  Update period for user 1 since we allocated 500 to February for Rent expense
UPDATE `budgetdbv2`.`period` SET `availableFund` = '500' WHERE (`id` = '1');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '2500' WHERE (`id` = '2');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '4500.00' WHERE (`id` = '3');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '6500.00' WHERE (`id` = '4');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '8500.00' WHERE (`id` = '5');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '10500.00' WHERE (`id` = '6');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '12500.00' WHERE (`id` = '7');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '14500' WHERE (`id` = '8');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '16500' WHERE (`id` = '9');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '18500' WHERE (`id` = '10');
UPDATE `budgetdbv2`.`period` SET `availableFund` = '20500' WHERE (`id` = '11');
