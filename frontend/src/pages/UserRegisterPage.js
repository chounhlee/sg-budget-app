import React, {Component} from "react"
import "../styles/register_page.css"
import UserRegisterForm from "../components/UserRegisterForm"
import {withRouter} from "react-router";

const SERVICE_URL = "http://localhost:8080";

class UserRegisterPage extends Component {
  state = {
    userRegisterData: {
      "username": "",
      "userPassword": "",
      "monthlyIncome": 0.00,
      "availableFund": 0.00
    }
  }

  componentDidMount() {

  }

  handleRegister = (e) => {
    fetch(`${SERVICE_URL}/register`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.userRegisterData)
    })
      .then(response => {
        if (response.status !== 201) {
          throw new Error("Error register");
        }
      })
      .then(() => {
        console.log("Register success");
        this.props.history.push('/login');

      })
      .catch((error) => {
        // Display appropriate message
        console.log("error register");
      });
  }

  handleChange = (e) => {
    let inputName = e.target.name;
    let inputValue = e.target.value;
    let userRegisterData = this.state.userRegisterData;


    if (userRegisterData.hasOwnProperty(inputName)
      && (inputName === "monthlyIncome" || inputName === "availableFund")) {
      // Parse float for income and fund
      userRegisterData[inputName] = parseFloat(parseFloat(inputValue).toFixed(2));

    } else {
      userRegisterData[inputName] = inputValue;
    }

    this.setState({userRegisterData: userRegisterData})
  }

  render() {
    return (
      <div id="register_page" className="App-page">
        <h2 id="header">
          Register
          <UserRegisterForm
            userRegisterData={this.state.userRegisterData}
            handleRegister={this.handleRegister}
            handleChange={this.handleChange} />
        </h2>
      </div>
    )

  }
}

export default withRouter(UserRegisterPage)