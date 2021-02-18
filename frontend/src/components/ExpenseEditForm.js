import React, {Fragment, Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'
import "../styles/editExpense.css"
import {withCookies} from "react-cookie";

class ExpenseEditForm extends Component {

  render() {
    let {editExpenseData, handleChange, handleSubmit} = this.props;

    return (
      <main>
        <Fragment>
          <div className="form-group">
            <input name="expenseName" type="text" id="expNameInput" className="form-control form-control-lg"
                   placeholder="Expense name"
                   value={editExpenseData.expenseName}
                   onChange={handleChange} />
          </div>
          <div className="form-group">
            <input name="amount" type="text" id="amtInput" className="form-control form-control-lg"
                   placeholder="Amount"
                   value={editExpenseData.amount}
                   onChange={handleChange} />
          </div>
          <label className='radioOptions'>
            <input type='radio' name='isMonthly'
                   value='true'
                   onChange={handleChange}
                   checked={((editExpenseData.isMonthly + "") === "true")}
            />
            Recurring
          </label>
          <label className='radioOptions'>
            <input type='radio' name='isMonthly'
                   value='false'
                   onChange={handleChange}
                   checked={((editExpenseData.isMonthly + "") === "false")}
            />
            One Time
          </label><br />

          <a id="closeButton" href="/home" target="_self">
            <Button> Close </Button>
          </a>
          <Button id="addExp"
                  onClick={handleSubmit}>Submit</Button>

        </Fragment>

      </main>
    )
  }
}


export default withCookies(ExpenseEditForm)