import React, {Fragment, Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'
import {Container, Row, Col} from 'react-bootstrap'
import "../styles/editExpense.css"
import {withCookies} from "react-cookie";


class EditExpenseModal extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    let {expenseData, handleChange, handleSubmit} = this.props;

    return (
      <main>
        <Fragment>
          <div className="form-group">
            <input name="expenses" type="text" id="expNameInput" className="form-control form-control-lg"
                   placeholder="Expense name"
                   value={expenseData.expenseName}
                   onChange={handleChange} />
          </div>
          <div className="form-group">
            <input name="amount" type="text" id="amtInput" className="form-control form-control-lg"
                   placeholder="Amount"
                   value={expenseData.amount}
                   onChange={handleChange} />
          </div>
          <label className='radioOptions'>
            <input type='radio' name='monthly' value='true'
                   onChange={handleChange}
                   checked={(expenseData.monthly === true)}
              />
            Recurring
          </label>
          <label className='radioOptions'>
            <input type='radio' name='monthly' value='false'
                   onChange={handleChange}
            checked={(expenseData.monthly === false)}/>
            One Time
          </label><br />

          <a id="closeButton" href="/home" target="_self">
            <Button> Close </Button>
          </a>
          <Button id="addExp"
          onSubmit={handleSubmit}>Submit</Button>

        </Fragment>

      </main>
    )
  }
}


export default withCookies(EditExpenseModal)