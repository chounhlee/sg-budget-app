DROP DATABASE IF EXISTS budgetdbv1;
CREATE DATABASE budgetdbv1;
use budgetdbv1;

CREATE TABLE User(
    username varchar(30) primary key,
    userPassword varchar(30),
    monthlyIncome decimal(10,2),
    availableFund decimal(10,2)
);

CREATE TABLE Expense(
    id int primary key auto_increment,    
    username varchar(30),
    expenseName varchar(100),
    isMonthly boolean,
    amount decimal(10,2),
    dateUpdated date,
    allocated decimal(10,2),
    remaining decimal(10,2),
	foreign key (username) references User(username)    
);
