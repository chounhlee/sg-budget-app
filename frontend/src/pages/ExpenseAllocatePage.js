import React, {Component} from 'react'
import {Button} from "react-bootstrap";
import {withCookies} from "react-cookie";
import {withRouter} from "react-router";
import "../styles/allocate.css"

const SERVICE_URL = "http://localhost:8080";

class ExpenseAllocatePage extends Component {
  state = {
    allocateData: {
      "username": "",
      "expenseId": 0,
      "allocated": 0,
      "month": "2020-02-01"
    }
  }

  componentDidMount() {
    let allocateData = this.state.allocateData;
    allocateData.username = this.props.cookies.get("username");
    allocateData.expenseId = this.props.expenseId;
  }

  handleAllocateSubmit = (e) => {
    console.log("Allocating to Expense");
    e.preventDefault();

    fetch(`${SERVICE_URL}/expenses/allocate`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.allocateData)
    })
      .then(response => response.json())
      .then(data => {
        console.log("Allocate success");
        this.props.history.push('/home');
      });
  }

  handleAllocateChange = (e) => {
    let inputName = e.target.name;
    let inputValue = e.target.value;
    let allocateData = this.state.allocateData;
    console.log(inputName);
    if (allocateData.hasOwnProperty(inputName)) {
      allocateData[inputName] = parseFloat(parseFloat(inputValue).toFixed(2))
      this.setState({allocateData: allocateData})
    }
  }

  render() {

    return (
      <>
        <div id="allocate_page" className="App-page">
          <h2 id="allocateHeader">
            Allocate Funds
            <main>
              <div className="form-group">
                <input name="allocated" type="number" id="expNameInput" className="form-control form-control-lg"
                       placeholder="Allocate Funds"
                       onChange={this.handleAllocateChange}
                       value={this.state.allocateData.allocated} />
              </div>

              <a id="closeButton" href="/home" target="_self">
                <Button> Close </Button>
              </a>

              <Button id="addExp" onClick={this.handleAllocateSubmit}>Submit</Button>

            </main>
          </h2>
        </div>
      </>
    )
  }
}

export default withRouter(withCookies(ExpenseAllocatePage))