import React, {Fragment, Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'
import "../styles/editIncome.css"

class UserEditIncomeForm extends Component {
  constructor(props) {
    super(props)
    this.state = {
      monthlyIncome: '',
      availableFunds: '',
    }
  }

  render() {
    return (
      <main>
        <Fragment>
          <div className="form-group">
            <input name="expenses" type="text" id="expNameInput" className="form-control form-control-lg"
                   placeholder="Monthly Income" onChange={this.handleOnChange} />
          </div>
          <div className="form-group">
            <input name="amount" type="text" id="amtInput" className="form-control form-control-lg"
                   placeholder="Available Funds" onChange={this.handleOnChange} />
          </div>

          <a id="closeButton" href="http://localhost:3000/home" target="_self">
            <Button> Close </Button>
          </a>


          <Button id="addExp">Submit</Button>

        </Fragment>

      </main>
    )
  }
}


export default UserEditIncomeForm