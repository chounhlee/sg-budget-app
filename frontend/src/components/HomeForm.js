import React, {Component} from 'react'
import {Navbar, Nav, Button, Table, Form} from 'react-bootstrap'
import ExpenseTable from './ExpenseTable'
import AddExpenseForm from './AddExpenseForm'

import "../styles/home.css"


import { Container, Row, Col } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';


class HomeForm extends React.Component{
  constructor(){
  super();

  var today = new Date(),

  date = (today.getMonth() + 1) + '-' + today.getDate() + '-' + today.getFullYear();

  this.state = {
    expenses: '',
    amount: '',
    recurring: '',
    infos: [],
    currentDate: date


  }
};

handleFormSubmit = (e) => {
  e.preventDefault();

  let infos = [...this.state.infos];

  infos.push({
    expenses: this.state.expenses,
    amount: this.state.amount,
    recurring: this.state.recurring
  });

  this.setState({
    infos,
    expenses: '',
    amount: '',
    recurring:''
  });
};

handleInputChange = (e) => {
  let input = e.target;
  let name = e.target.name;
  let value = input.value;

  this.setState({
    [name]: value
  })
};



render() {
  return (
    <Container fluid> 
    <Navbar id="nav" bg="dark" variant="dark" >Budget App
      <Nav className="links">
        <Nav.Link href="http://localhost:3000/login"> Logout </Nav.Link>
      </Nav>
    </Navbar>
    <Row>
      <Col>
        <h1 className="text-center">Budget Application</h1>

        <h3>{this.state.currentDate}</h3>
        <h3>Monthly Income:xxx

          <a id="editIncomeButton" href="http://localhost:3000/editIncome" target="_self">
                  <Button> Edit </Button>
          </a>

        </h3>
        <h3>Available Fund:xxx</h3>
      </Col>
    </Row>
    <hr />
    <Row>
      <Col sm={8}>
        <h2>My Expense Info</h2>
        <ExpenseTable />
      </Col>
      <Col sm={4}>
        <h2>Add New Expense</h2>
        <AddExpenseForm />
        
      </Col>
      <div className="App">
      <Table infos={ this.state.infos }/>
      <Form handleFormSubmit={ this.handleFormSubmit } 
        handleInputChange={ this.handleInputChange }
        newExpenseName={ this.state.expenses }
        newAmount={ this.state.amount }
        newRecurring={ this.state.recurring } />
        
    </div>
    </Row>

    </Container>
);
  }
}

export default HomeForm;
