DROP DATABASE IF EXISTS budgetdbv2;
CREATE DATABASE budgetdbv2;
use budgetdbv2;

CREATE TABLE User(
    username varchar(30) primary key,
    userPassword varchar(30),
    monthlyIncome decimal(10,2)
);


CREATE TABLE Period(
    id int primary key auto_increment,    
    periodDate date,
    availableFund decimal(10,2),
    username varchar(30),
    FOREIGN KEY (username) REFERENCES user(username)
);

CREATE TABLE Expense(
    id int primary key auto_increment,    
    expenseName varchar(30),
    isMonthly boolean,
    amount decimal(10,2),
    dateCreated date
);

CREATE TABLE period_expense(
    periodId INT NOT NULL,
    expenseId INT NOT NULL,
    allocated decimal(10,2),
    remaining decimal(10,2),
    PRIMARY KEY(periodId, expenseId),
    FOREIGN KEY (periodId) REFERENCES period(id),
    FOREIGN KEY (expenseId) REFERENCES expense(id)
);




