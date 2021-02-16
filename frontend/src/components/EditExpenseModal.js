import React, {Fragment, Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'
import { Container, Row, Col } from 'react-bootstrap'

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
          <label>Expense Name</label>
          <input name= "expenses" type="text" id="expNameInput" className="form-control form-control-lg" onChange={this.handleOnChange} />
        </div>
        <div className="form-group">
          <label>Amount</label>
          <input name="amount" type="text" id="amtInput" className="form-control form-control-lg" onChange={this.handleOnChange} />
        </div>
        
                
    ​
                <label>
                    <input type='radio' name='Recurring' id="yesRadio" value='Yes' onChange={this.handleOnChange} />Recurring
                </label>
                <label>
                    <input type='radio' name='Recurring' className="noRadio" value='No' onChange={this.handleOnChange} />One Time 
                    </label><br />
                    
                    <Button id="addExp">Close</Button>
                    <Button id="addExp">Submit</Button>
                    
                    </Fragment>
                    
        </main>
                    
)}
    }




export default EditExpenseModal