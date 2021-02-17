import React, {Component} from 'react'
import {Table, Button} from 'react-bootstrap'
import {Link} from 'react-router-dom';

const ExpenseTableHeader = () => {
  return (
    <tr>
      <th>Expenses</th>
      <th>Amount</th>
      <th>Allocated</th>
      <th>Remaining</th>
      <th>Recurring</th>
      <th></th>
    </tr>
  );
}

const ExpenseTableRow = ({expense}) => {
  return (

    <tr>
      <td>{expense.expenseName}</td>
      <td>${expense.amount}</td>
      <td>${expense.allocated}</td>
      <td>${expense.remaining}</td>
      <td>{(expense.monthly) ? "Yes" : ""}</td>

      <td className="pl-0 pr-0">
        <a id="editExpense" href="http://localhost:3000/editExpense" target="_self">
          <Button> Edit </Button>
        </a>
        <Button>Delete</Button>
        <a id="allocateFunds" href="http://localhost:3000/allocate" target="_self">
          <Button> Allocate </Button>
        </a>
      </td>
    </tr>

  );
}

class ExpenseTable extends Component {
  constructor(props) {
    super(props);
  }

  static defaultProps = {
    expenses: [
      {
        "id": 1,
        "username": "",
        "expenseName": "",
        "amount": 0.00,
        "allocated": 0.00,
        "remaining": 0.00,
        "dateUpdated": "",
        "monthly": false
      }
    ]
  }

  render() {
    const infos = this.props.infos;

    return (
      <Table striped bordered responsive>
        <thead>
        <ExpenseTableHeader />
        </thead>

        <tbody>
        {this.props.expenses.map((expense, i) => {

          return <ExpenseTableRow key={i}
                                  expense={expense} />
        })}

        </tbody>

      </Table>
    )
  }
}

export default ExpenseTable