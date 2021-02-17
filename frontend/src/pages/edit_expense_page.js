import React, {Component} from 'react'
import EditExpenseModal from '../components/EditExpenseModal'
import {withCookies} from "react-cookie";

const SERVICE_URL = "http://localhost:8080";

class EditExpense extends Component {
  state = {
    currentDate: "",
    loading: false,
    expenseData:
      {
        "id": 1,
        "username": "",
        "expenseName": "",
        "amount": 0.00,
        "allocated": 0.00,
        "remaining": 0.00,
        "dateUpdated": "2020-02-01",
        "isMonthly": true
      },
    editExpenseData: {
      "username": this.props.cookies.get("username"),
      "expenseName": "",
      "isMonthly": false,
      "amount": 0,
      "month": "2020-01-01"
    }
  }
  handleFormSubmit = (e) => {
  
  };

  handleInputChange = (e) => {

  };

  componentDidMount() {
    this.loadExpenseData();
  }

  loadExpenseData() {
    const {expenseId} = this.props;
    const username = this.props.cookies.get("username");

    fetch(`${SERVICE_URL}/expenses/${expenseId}?username=${username}&month=2020-02-01`, {
      method: 'GET',
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(data => data.json())
      .then(data => {
        this.setState(
          {expenseData: data, loading: false}
        )
        console.log("From edit expense:" + data);
        console.log(data);
      });
  }

  render() {
    return (
      <div id="edit_expense_page" className="App-page">
        <h2 id="expenseHeader">
          Edit Expense
          <EditExpenseModal expenseData={this.state.expenseData}
                            handleChange={this.handleInputChange}
                            handleSubmit={this.handleFormSubmit} />

        </h2>
      </div>
    )
  }
}

export default withCookies(EditExpense)