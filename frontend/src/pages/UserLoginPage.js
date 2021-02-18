import React, {Component} from 'react'
import "../styles/login_page.css"
import UserLoginForm from '../components/UserLoginForm'
import {withRouter} from "react-router";
import {withCookies} from "react-cookie";
import ErrorsAlert from "../components/ErrorsAlert";
import {Col, Container, Row} from "react-bootstrap";

const SERVICE_URL = "http://localhost:8080";

class LoginPage extends Component {
  state = {
    userLoginData: {
      username: '',
      password: ''
    },
    errors: []

  }


  handleChange = (e) => {
    const {name, value} = e.target
    let userLoginData = this.state.userLoginData;

    if (userLoginData.hasOwnProperty(name)) {
      userLoginData[name] = value;
      this.setState({userLoginData: userLoginData})
    }
  }

  handleLogin = (e) => {
    e.preventDefault();
    this.setState({errors: []});

    fetch(`${SERVICE_URL}/login`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.userLoginData)
    })
    .then(response => {
        if (!response.ok) {
          response.json().then((data) => {
            let errors = this.state.errors;
            errors.push({message: data.message});
            this.setState({errors: errors});
          })

          throw new Error();
        }

      })
      .then(() => {
        this.props.cookies.set("username", this.state.userLoginData.username);
        this.props.history.push('/home');
      })
      .catch((error) => {
        this.props.history.push('/login');
      })
  }

  render() {
    return (
      <>
        <div id="login_page" className="App-page">
          <div id="loginHeader">
            <h2>Login</h2>
            <ErrorsAlert errors={this.state.errors} />
            <UserLoginForm handleChange={this.handleChange}
                           handleLogin={this.handleLogin} />
          </div>
        </div>
      </>
    )

  }
}

export default withCookies(withRouter(LoginPage))