import React, {Component} from 'react'
import {Table, Button} from 'react-bootstrap'
import ExpenseRow from "./ExpenseRow";

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
    const {handleDelete} = this.props;

    return (
      <Table striped bordered responsive>
        <thead>
        <ExpenseTableHeader />
        </thead>

        <tbody>
        {this.props.expenses.map((expense, i) => {

          return <ExpenseRow key={i}
                             expense={expense}
                             handleDelete={handleDelete} />
        })}

        </tbody>

      </Table>
    )
  }
}

export default ExpenseTable