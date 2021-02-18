import React, {Component} from 'react'
import "../styles/login_page.css"
import UserLoginForm from '../components/UserLoginForm'
import {withRouter} from "react-router";
import {withCookies} from "react-cookie";

const SERVICE_URL = "http://localhost:8080";

class LoginPage extends Component {
  state = {
    userLoginData: {
      username: '',
      password: ''
    }
  }

  constructor(props) {
    super(props);
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

    fetch(`${SERVICE_URL}/login`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state.userLoginData)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Unable to login');
        }
      })
      .then(data => {
        this.props.cookies.set("username", this.state.userLoginData.username);
        this.props.history.push('/home');
      })
      .catch((error) => {
        this.props.history.push('/login');
      })
  }

  render() {
    return (
      <div id="login_page" className="App-page">
        <h2 id="loginHeader">
          Login
          <UserLoginForm handleChange={this.handleChange}
                         handleLogin={this.handleLogin} />
        </h2>
      </div>
    )

  }
}

export default withCookies(withRouter(LoginPage))