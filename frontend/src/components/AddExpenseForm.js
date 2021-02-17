import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, ButtonGroup, Form, ToggleButton} from 'react-bootstrap'
import "../styles/editExpense.css"

class AddExpenseForm extends React.Component {

  render() {
    let {expenseData, handleChange, handleSubmit} = this.props;

    return (
      <div id="Form">
        <Form onSubmit={handleSubmit}>
          <label htmlFor="expenses">

            <input className="expInputs" id="expenses" placeholder="Expense Name"
                   type="text"
                   name="expenseName"
                   value={expenseData.expenseName}
                   onChange={handleChange} />
          </label><br />

          <label htmlFor="amount">
            <input className="expInputs" id="amount" placeholder="Amount" type="amount"
                   name="amount"
                   value={expenseData.amount}
                   onChange={handleChange} />
          </label><br />

          <label className='radioOptions'>
            <input type='radio' name='isMonthly'
                   value='true'
                   onChange={handleChange} />Recurring
          </label>
          <label className='radioOptions'>
            <input type='radio' name='isMonthly'
                   value='false'
                   onChange={handleChange} />One
            Time
          </label><br />

          <Button type="submit"
                  value="Submit"
                  onSubmit={handleSubmit}>Add Expense</Button>
        </Form>
      </div>
    );
  }
}

export default AddExpenseForm