import React, {Component} from "react"
import "../styles/register_page.css"
import UserRegisterForm from "../components/UserRegisterForm"
import {withRouter} from "react-router";
import ErrorsAlert from "../components/ErrorsAlert";

const SERVICE_URL = "http://localhost:8080";

class UserRegisterPage extends Component {
  state = {
    userRegisterData: {
      "username": "",
      "userPassword": "",
      "monthlyIncome": 0.00,
      "availableFund": 0.00
    },
    errors: []
  }

  componentDidMount() {

  }

  handleRegister = (e) => {

    this.setState({errors: []});

    fetch(`${SERVICE_URL}/register`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.userRegisterData)
    })
      .then(response => {
        if (response.status !== 201) {
          response.json().then((data) => {
            let errors = this.state.errors;
            errors.push({message: data.message});
            this.setState({errors: errors});
          })

          throw new Error();
        }
      })
      .then(() => {
        console.log("Register success");
        this.props.history.push('/login');

      })
      .catch((error) => {

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
        <div id="header">
          <h2>Register</h2>
          <ErrorsAlert errors={this.state.errors} />
          <UserRegisterForm
            userRegisterData={this.state.userRegisterData}
            handleRegister={this.handleRegister}
            handleChange={this.handleChange} />
        </div>
      </div>
    )

  }
}

export default withRouter(UserRegisterPage)