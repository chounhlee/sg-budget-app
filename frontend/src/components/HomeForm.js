import React, {Component} from 'react'
import {Navbar, Nav, Button, Table, Form} from 'react-bootstrap'
import ExpenseTable from './ExpenseTable'
import AddExpenseForm from './AddExpenseForm'

import "../styles/home.css"

import {Container, Row, Col} from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';

const SERVICE_URL = "http://localhost:8080";

class HomeForm extends Component {
  state = {
    currentDate: "",
    loading: false,
    userData: {
      "username": "user1",
      "monthlyIncome": 5000.00,
      "availableFund": 10000.00
    },
    expensesData: [
      {
        "id": 1,
        "username": "user1",
        "expenseName": "Payment",
        "amount": 1000.00,
        "allocated": 0.00,
        "remaining": 1000.00,
        "dateUpdated": "2020-02-01",
        "monthly": true
      }],
    newExpenseData: {
      "username": "user1",
      "expenseName": "",
      "isMonthly": false,
      "amount": 0,
      "month": "2020-01-01"
    }
  }

  constructor(props) {
    super(props);

    const today = new Date();
    const date = (today.getMonth() + 1) + '-' + today.getDate() + '-' + today.getFullYear();
    this.state.currentDate = date;
  };

  componentDidMount() {
    this.loadExpensesData();
  }

  loadExpensesData() {
    this.setState({loading: true});
    fetch(`${SERVICE_URL}/expenses?username=user1&month=2020-02-01`, {
      method: 'GET',
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(data => data.json())
      .then(data => this.setState(
        {expensesData: data, loading: false}
      ));
  }

  handleFormSubmit = (e) => {
    console.log("Adding Expense");
    e.preventDefault();

    fetch(`${SERVICE_URL}/expenses`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.newExpenseData)
    })
      .then(response => response.json())
      .then(data => {
        this.setState(
          { newExpenseData: {
              "username": "user1",
              "expenseName": "",
              "isMonthly": false,
              "amount": 0,
              "month": "2020-01-01"
            }, loading: false}
        )

        this.loadExpensesData();
      });
  };

  handleInputChange = (e) => {
    console.log("New Expense data is changing");
    let inputName = e.target.name;
    let inputValue = e.target.value;
    let expenseData = this.state.newExpenseData;
    console.log(inputName);
    console.log(inputValue);
    if (expenseData.hasOwnProperty(inputName)) {
      expenseData[inputName] = inputValue;
      this.setState({newExpenseData: expenseData})
    }

    console.log(this.state.newExpenseData);
  };

  render() {
    return (
      <Container fluid>
        <Navbar id="nav" bg="dark" variant="dark">Budget App
          <Nav className="links">
            <Nav.Link href="/login"> Logout </Nav.Link>
          </Nav>
        </Navbar>
        <Row>
          <Col>
            <h1 className="text-center">Budget Application</h1>

            <h4>{this.state.currentDate}</h4>

            <h4>Monthly Income: ${this.state.userData.monthlyIncome}
              <a id="editIncomeButton" href="http://localhost:3000/editIncome" target="_self">
                <Button> Edit </Button>
              </a>
            </h4>

            <h4>Available Fund: ${this.state.userData.availableFund}</h4>
          </Col>
        </Row>
        <hr />
        <Row>
          <Col sm={9}>
            <h5>My Expenses</h5>
            <ExpenseTable expenses={this.state.expensesData} />
          </Col>

          <Col sm={3}>
            <h5>Add New Expense</h5>
            <AddExpenseForm
              expenseData={this.state.newExpenseData}
              handleChange={this.handleInputChange}
              handleSubmit={this.handleFormSubmit} />

          </Col>
          {/*<div className="App">*/}
          {/*  <Table infos={this.state.expensesData} />*/}
          {/*  <Form handleFormSubmit={this.handleFormSubmit}*/}
          {/*        onChange={this.handleInputChange}*/}
          {/*        newExpenseName={this.state.expenses}*/}
          {/*        newAmount={this.state.amount}*/}
          {/*        newRecurring={this.state.recurring} />*/}
          {/*</div>*/}
        </Row>

      </Container>
    );
  }
}

export default HomeForm;
