import React, {Component} from 'react'
import {Button, Col, Container, Nav, Navbar, Row} from "react-bootstrap";
import ExpenseTable from "../components/ExpenseTable";
import ExpenseAddForm from "../components/ExpenseAddForm";
import {withCookies} from "react-cookie";
import {withRouter} from "react-router";
import "../styles/home.css";
const SERVICE_URL = "http://localhost:8080";

class HomePage extends Component {
  state = {
    currentDate: "",
    loading: false,
    userIncomeAndFundData: {
      "monthlyIncome": 0.00,
      "availableFund": 0.00
    },
    expensesData: [
      {
        "id": 0,
        "username": "",
        "expenseName": "",
        "amount": 0.00,
        "allocated": 0.00,
        "remaining": 0.00,
        "dateUpdated": "2020-02-01",
        "isMonthly": true
      }],
    newExpenseData: {
      "username": "",
      "expenseName": "",
      "isMonthly": false,
      "amount": 0,
      "month": "2020-01-01"
    }
  }

  constructor(props) {
    super(props);

    const today = new Date();
    const date = (today.getMonth() + 1) + '-' + today.getFullYear();
    this.state.currentDate = date;
  };

  componentDidMount() {
    let username = this.props.cookies.get("username");

    if (!username) {
      this.props.history.push('/login');
      console.log("User is not logged in");
    } else {
      let newExpenseData = this.state.newExpenseData;
      newExpenseData.username = this.props.cookies.get("username");
      this.setState({newExpenseData: newExpenseData});

      this.loadHomePageData();
    }
  }

  loadHomePageData() {
    this.loadExpensesData();
    this.loadUserIncomeAndFundData();
  }

  loadExpensesData() {
    this.setState({loading: true});
    let username = this.props.cookies.get("username");
    fetch(`${SERVICE_URL}/expenses?username=${username}&month=2020-02-01`, {
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

  loadUserIncomeAndFundData() {
    this.setState({loading: true});
    let username = this.props.cookies.get("username");
    fetch(`${SERVICE_URL}/income?username=${username}`, {
      method: 'GET',
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(data => data.json())
      .then(data => this.setState(
        {userIncomeAndFundData: data, loading: false}
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
              "username":  this.props.cookies.get("username"),
              "expenseName": "",
              "isMonthly": false,
              "amount": 0,
              "month": "2020-01-01"
            }, loading: false}
        )

        this.loadHomePageData();
      });
  };

  handleInputChange = (e) => {
    let inputName = e.target.name;
    let inputValue = e.target.value;
    let expenseData = this.state.newExpenseData;

    if (expenseData.hasOwnProperty(inputName)) {
      expenseData[inputName] = inputValue;
      this.setState({newExpenseData: expenseData})
    }
  };

  handleExpenseDelete = () => {
    this.loadHomePageData();
  }

  handleUserLogout = (e) => {
    e.preventDefault();
    console.log("logging out");
    fetch(`${SERVICE_URL}/logout`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({username: this.props.cookies.get("username")})
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Unable to logout');
        }
      })
      .then(data => {
        this.props.cookies.set("username", "");
        this.props.history.push('/login');
      })
      .catch((error) => {
        this.props.history.push('/home');
      })
  }

  render() {
    return (
      <div id="home_page" className="App-page">

        <Container fluid>
          <Navbar id="nav" bg="dark" variant="dark" className="mb-5">Budget App
            <Nav className="links">
              <Nav.Link id="logoutLink" onClick={this.handleUserLogout}> Logout </Nav.Link>
            </Nav>
          </Navbar>
          <Row className="text-left">
            <Col>
              <h4>{this.state.currentDate}</h4>

              <h4>Monthly Income: ${this.state.userIncomeAndFundData.monthlyIncome}
                <a id="editIncomeButton" href="/editIncome" target="_self">
                  <Button> Edit </Button>
                </a>
              </h4>

              <h4>Available Fund: ${this.state.userIncomeAndFundData.availableFund}</h4>
            </Col>
          </Row>
          <hr />
          <Row>
            <Col sm={9}>
              <h5>My Expenses</h5>
              <ExpenseTable
                expenses={this.state.expensesData}
                handleDelete={this.handleExpenseDelete} />
            </Col>

            <Col sm={3}>
              <h5>Add New Expense</h5>
              <ExpenseAddForm
                expenseData={this.state.newExpenseData}
                handleChange={this.handleInputChange}
                handleSubmit={this.handleFormSubmit} />

            </Col>

          </Row>

        </Container>
      </div>
    );
  }
}

export default withCookies(withRouter(HomePage))