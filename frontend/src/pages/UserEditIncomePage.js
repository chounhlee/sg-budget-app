import React, {Component} from 'react'
import UserEditIncomeForm from '../components/UserEditIncomeForm'
import {withCookies} from "react-cookie";
import {withRouter} from "react-router";

const SERVICE_URL = "http://localhost:8080";

class UserEditIncomePage extends Component {
  state = {
    userIncomeAndFundData: {
      "username": this.props.cookies.get("username"),
      "monthlyIncome": 0.00,
      "availableFund": 0.00,
      "month": "2020-02-02"
    },
  }

  componentDidMount() {
    this.loadUserIncomeAndFundData();
  }

  loadUserIncomeAndFundData() {
    this.setState({loading: true});
    let username = this.props.cookies.get("username");
    fetch(`${SERVICE_URL}/income?username=${username}`, {
      method: 'GET',
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(data => data.json())
      .then(data =>  {
        let userIncomeAndFundData = this.state.userIncomeAndFundData;
        userIncomeAndFundData.availableFund = data.availableFund;
        userIncomeAndFundData.monthlyIncome = data.monthlyIncome;
        this.setState(
          {userIncomeAndFundData: userIncomeAndFundData, loading: false}
        )
      });
  }

  handleChange = (e) => {
    let inputName = e.target.name;
    let inputValue = e.target.value;
    let userIncomeAndFundData = this.state.userIncomeAndFundData;

    if (userIncomeAndFundData.hasOwnProperty(inputName)) {
      userIncomeAndFundData[inputName] = parseFloat(parseFloat(inputValue).toFixed(2));
      this.setState({userIncomeAndFundData: userIncomeAndFundData})
    }
    console.log(this.state.userIncomeAndFundData);
  }

  handleSubmit = (e) => {
    console.log("Editing Income");

    fetch(`${SERVICE_URL}/income`, {
      method: 'PUT',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.userIncomeAndFundData)
    })
      .then(response => response.json())
      .then(data => {
        console.log("Edit income success");
        this.props.history.push('/home');

      });
  }

  render() {
    return (
      <div id="edit_income_page" className="App-page">
        <h2 id="incomeHeader">
          Edit Income and Fund
          <UserEditIncomeForm
            userIncomeAndFundData={this.state.userIncomeAndFundData}
            handleChange={this.handleChange}
            handleSubmit={this.handleSubmit} />
        </h2>
      </div>
    )
  }
}

export default withRouter(withCookies(UserEditIncomePage))