import React, { Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'
import "../styles/editIncome.css"

class UserEditIncomeForm extends Component {

  render() {
    const {userIncomeAndFundData, handleChange, handleSubmit} = this.props;
    return (
      <main>
          <div className="form-group">
            <input name="monthlyIncome" type="number" className="form-control form-control-lg"
                   placeholder="Monthly Income"
                   onChange={handleChange}
                   value={userIncomeAndFundData.monthlyIncome} />
          </div>
          <div className="form-group">
            <input name="availableFund" type="number"  className="form-control form-control-lg"
                   placeholder="Available Funds"
                   onChange={handleChange}
                   value={userIncomeAndFundData.availableFund} />
          </div>

          <a  href="/home" target="_self">
            <Button> Close </Button>
          </a>


          <Button onClick={handleSubmit}>Submit</Button>
      </main>
    )
  }
}


export default UserEditIncomeForm