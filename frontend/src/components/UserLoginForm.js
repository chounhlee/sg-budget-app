import React from 'react';
import {Form, Button} from 'react-bootstrap'
import "../styles/LoginForm.css"

class UserLoginForm extends React.Component {
  render() {
    const {handleChange, handleLogin} = this.props;
    return (
      <Form>
        <Form.Group controlId="loginUsername">
          <Form.Label/>
          <Form.Control type="text" placeholder="Username" name="username" onChange={handleChange} />
        </Form.Group>

        <Form.Group controlId="loginPassword">
          <Form.Label/>
          <Form.Control type="password" placeholder="Password" name="password" onChange={handleChange} />
        </Form.Group>

        <a  href="/home" target="_self">
          <Button onClick={handleLogin}> Login </Button>
        </a>

        <a  href="/register" target="_self">
          <Button> Register </Button>
        </a>
      </Form>
    )
  }
}

export default UserLoginForm