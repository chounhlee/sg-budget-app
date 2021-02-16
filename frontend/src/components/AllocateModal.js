import React, {Fragment, Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap'
import {Container, Row, Col} from 'react-bootstrap'
import "../styles/allocate.css"


class AllocateModal extends Component {
  constructor() {
    super()
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
                   placeholder="Allocate Funds" onChange={this.handleOnChange} />
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


export default AllocateModal