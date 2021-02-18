import React from 'react';
import {Form, Button} from 'react-bootstrap'
import "../styles/LoginForm.css"


class UserLoginForm extends React.Component {
  constructor() {
    super()
    this.state = {
      username: '',
      password: ''
    }
    this.handleOnChange = this.handleOnChange.bind(this)
  }

  handleOnChange(event) {
    const {name, value} = event.target

    this.setState({
      [name]: value
    })
  }

  render() {
    return (
      <Form>
        <Form.Group controlId="loginUsername">
          <Form.Label></Form.Label>
          <Form.Control type="text" placeholder="Username" name="username" onChange={this.handleOnChange} />
        </Form.Group>
        <Form.Group controlId="loginPassword">
          <Form.Label></Form.Label>
          <Form.Control type="text" placeholder="Password" name="password" onChange={this.handleOnChange} />
        </Form.Group>

        <a id="loginButton" href="http://localhost:3000/home" target="_self">
          <Button> Login </Button>
        </a>

        <a id="registerButton" href="http://localhost:3000/register" target="_self">
          <Button> Register </Button>
        </a>


      </Form>
    )
  }
}

export default UserLoginForm