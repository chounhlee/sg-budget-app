import React from 'react';
import {Form, Button} from 'react-bootstrap'


class UserRegisterForm extends React.Component {

  render() {
    const {userRegisterData, handleRegister, handleChange} = this.props;

    return (
      <Form>
        <Form.Group controlId="registerUsername">
          <Form.Label />
          <Form.Control type="text" placeholder="Username"
                        name="username"
                        onChange={handleChange}
                        value={userRegisterData.username} />
        </Form.Group>
        <Form.Group controlId="registerPassword">
          <Form.Label />
          <Form.Control type="password" placeholder="Password"
                        name="userPassword"
                        onChange={handleChange}
                        value={userRegisterData.userPassword}/>
        </Form.Group>
        <Form.Group controlId="registerMonthlyIncome">
          <Form.Label>Monthly Income</Form.Label>
          <Form.Control type="number" placeholder="Monthly Income"
                        name="monthlyIncome"
                        onChange={handleChange}
                        value={userRegisterData.monthlyIncome}/>
        </Form.Group>
        <Form.Group controlId="registerAvailableFunds">
          <Form.Label>Available Fund</Form.Label>
          <Form.Control type="number" placeholder="Available Fund"
                        name="availableFund"
                        onChange={handleChange}
                        value={userRegisterData.availableFund}/>
        </Form.Group>
        <Button variant="primary" className="btn-primary"
                onClick={handleRegister}>
          Register
        </Button>

        <a href="/login" target="_self">
          <Button variant="primary" className="btn-primary">
            Login
          </Button>
        </a>
      </Form>
    )
  }
}

export default UserRegisterForm