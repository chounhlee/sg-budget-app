import {Button} from "react-bootstrap";
import React, {Component} from "react";
import {withCookies} from "react-cookie";
import {withRouter} from "react-router";

const SERVICE_URL = "http://localhost:8080";

class ExpenseRow extends Component {
  state = {
    deleteExpenseData: {
      expenseId: 0,
      username: ""
    }
  }

  constructor(props) {
    super(props);
  }

  handleLocalDelete = (e) => {
    e.preventDefault();

    let deleteExpenseData = this.state.deleteExpenseData;
    deleteExpenseData.expenseId = this.props.expense.id;
    deleteExpenseData.username = this.props.cookies.get("username");
    this.setState(deleteExpenseData);

    fetch(`${SERVICE_URL}/expenses`, {
      method: 'DELETE',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.deleteExpenseData)
    })
      .then(response => response.json())
      .then(data => {
        console.log("Redirecting to home");
        this.props.history.push('/home');
        this.props.handleDelete();
      });
  }

  render() {
    const {expense} = this.props;

    return (
      <tr>
        <td>{expense.expenseName}</td>
        <td>${expense.amount}</td>
        <td>${expense.allocated}</td>
        <td>${expense.remaining}</td>
        <td>{(expense.isMonthly) ? "Yes" : ""}</td>

        <td className="pl-0 pr-0">
          <a id="editExpense" href={`expenses/${expense.id}/edit`} target="_self">
            <Button> Edit </Button>
          </a>
          <Button onClick={this.handleLocalDelete}>Delete</Button>
          <a id="allocateFunds" href={`expenses/${expense.id}/allocate`} target="_self">
            <Button> Allocate </Button>
          </a>
        </td>
      </tr>

    );
  }
}

export default withCookies(withRouter(ExpenseRow));