import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'



class AddExpenseForm extends React.Component{
  
  render() {
    return (
      <div id="Form">
        <form onSubmit={this.props.handleFormSubmit}>
          <label htmlFor="expenses">
          
          <input className="expInputs" id="expenses" placeholder="Expense Name"  value={this.props.newExpenseName} 
            type="text" name="expenses"
            onChange={this.props.handleInputChange} />
          </label><br/>

          <label for="amount">
          <input className="expInputs" id="amount" placeholder="Amount" value={this.props.newamount} 
            type="amount" name="amount"
            onChange={this.props.handleInputChange} />
          </label><br/>

          <label>
                  <input type='radio' name='Recurring' id="yesRadio" value='Yes' onChange={this.props.handleInputChange} />Recurring
          </label>
          <label>
                  <input type='radio' name='Recurring' className="noRadio" value='No' onChange={this.props.handleInputChange} />One Time 
            </label><br />

          <Button type="submit" value="Submit">Add Expense</Button>
        </form>
      </div>
    );
  }
}


export default AddExpenseForm