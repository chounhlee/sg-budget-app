use budgetdbv1testdb;
DROP TABLE  IF EXISTS `User`;

CREATE TABLE `User` (
    username varchar(30) primary key,
    userPassword varchar(30),
    monthlyIncome decimal(10,2),
    availableFund decimal(10,2)
);