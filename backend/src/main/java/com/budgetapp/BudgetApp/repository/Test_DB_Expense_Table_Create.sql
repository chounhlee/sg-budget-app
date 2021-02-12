use budgetdbv1testdb;
DROP TABLE IF EXISTS `Expense`;

CREATE TABLE `Expense`(
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
