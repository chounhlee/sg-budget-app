import React, {Fragment, Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'
import { Container, Row, Col } from 'react-bootstrap'
import "../styles/editExpense.css"


class EditExpenseModal extends Component{
  constructor() {
    super()
    this.state = {
        expenses: '',
        amount: '',
      }
  }


render()
{
    return (
        <main>
            <Fragment>
            <div className="form-group">
              <input name= "expenses" type="text" id="expNameInput" className="form-control form-control-lg" placeholder="Expense name" onChange={this.handleOnChange} />
            </div>
            <div className="form-group">
              <input name="amount" type="text" id="amtInput" className="form-control form-control-lg" placeholder="Amount" onChange={this.handleOnChange} />
            </div>
              <label className = 'radioOptions'>
                  <input type='radio' name='options' id="yesRadio" value='Yes' onChange={this.handleOnChange} />
                  Recurring
              </label>
              <label className = 'radioOptions'>
                  <input type='radio' name='options' id="noRadio" value='No' onChange={this.handleOnChange} />
                  One Time 
              </label><br />
                    
                <a id="closeButton" href="http://localhost:3000/home" target="_self">
                  <Button> Close </Button>
                </a>


                    <Button id="addExp">Submit</Button>
                    
                    </Fragment>            
                    
        </main>

                    
)}
    }




export default EditExpenseModal