import React, {Component} from 'react'
import EditExpenseModal from '../components/EditExpenseModal'
import {withCookies} from "react-cookie";
import {withRouter} from "react-router";

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
      "expenseId": 0,
      "username": "",
      "expenseName": "",
      "isMonthly": false,
      "amount": 0,
      "month": "2020-02-01"
    }

  }
  handleSubmit = (e) => {
    console.log("Editing Expense");
    e.preventDefault();

    fetch(`${SERVICE_URL}/expenses`, {
      method: 'PUT',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.editExpenseData)
    })
      .then(response => response.json())
      .then(data => {
        console.log("Expense edited success");
        this.props.history.push('/home');
      });
  };

  handleChange = (e) => {
    console.log("Expense data is changing");
    let inputName = e.target.name;
    let inputValue = e.target.value;
    let editExpenseData = this.state.editExpenseData;

    if (editExpenseData.hasOwnProperty(inputName)) {
      if (inputName === "isMonthly") {
        editExpenseData[inputName] = (inputValue === "true");
      } else {
        editExpenseData[inputName] = inputValue;
      }
      this.setState({editExpenseData: editExpenseData})
    }
    console.log("Edit expense data: ");
    console.log(this.state.editExpenseData);
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

        let editExpenseData = this.state.editExpenseData;
        editExpenseData.expenseId = data.id;
        editExpenseData.username = data.username;
        editExpenseData.expenseName = data.expenseName;
        editExpenseData.amount = data.amount;
        editExpenseData.isMonthly = data.isMonthly;
        editExpenseData.month = data.dateUpdated;

        this.setState(
          {editExpenseData: editExpenseData}
        );
      });
  }

  render() {
    return (
      <div id="edit_expense_page" className="App-page">
        <h2 id="expenseHeader">
          Edit Expense
          <EditExpenseModal
            editExpenseData={this.state.editExpenseData}
            handleChange={this.handleChange}
            handleSubmit={this.handleSubmit} />

        </h2>
      </div>
    )
  }
}

export default withCookies(withRouter(EditExpense))