use budgetdbv1;

--  Create 1 users
INSERT INTO `budgetdbv1`.`user` (`username`, `userPassword`, `monthlyIncome`, `availableFund`) VALUES ('user1', 'password', '2000', '1000');

--  Create 2 expense, one for recurring, and one for one-time
INSERT INTO `budgetdbv1`.`expense` (`username`, `expenseName`, `isMonthly`, `amount`, `dateUpdated`, `allocated`, `remaining`) VALUES ('user1', 'Rent', '1', '500', '2020-02-01', '0', '500');
INSERT INTO `budgetdbv1`.`expense` (`username`, `expenseName`, `isMonthly`, `amount`, `dateUpdated`, `allocated`,  `remaining`) VALUES ('user1', 'Spine Surgery', '0', '2000', '2020-02-01', '0','2000');


-- Allocate $500 to expense #1
UPDATE `budgetdbv1`.`expense` SET `allocated` = '500', `remaining` = '0' WHERE (`id` = '1');
UPDATE `budgetdbv1`.`user` SET `availableFund` = '500' WHERE (`username` = 'user1');


-- Get expense for user 1
SELECT * FROM budgetdbv1.expense
WHERE username = "user1";

-- Get income and available fund for user1
SELECT * FROM budgetdbv1.user
WHERE username = "user1";

-- Delete expense #1 and update available fund
DELETE FROM `budgetdbv1`.`expense` WHERE (`id` = '1');
UPDATE `budgetdbv1`.`user` SET `availableFund` = '1000' WHERE (`username` = 'user1');

-- Update income and available fund
UPDATE `budgetdbv1`.`user` SET `availableFund` = '1000', `monthlyIncome` = '3000' WHERE (`username` = 'user1');

-- Update expense #2, make sure backend update field correctly if allocate is higher than amount or remaining reflect new amount
UPDATE `budgetdbv1`.`expense` SET `expenseName` = 'Liver Surgery', `amount` = '5000.00', `allocated` = '0', `remaining` = '5000.00' WHERE (`id` = '2');
UPDATE `budgetdbv1`.`user` SET `availableFund` = '1000' WHERE (`username` = 'user1');


