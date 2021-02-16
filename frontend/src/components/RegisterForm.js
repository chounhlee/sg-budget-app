import React from 'react';
import { Form, Button } from 'react-bootstrap'


class RegisterForm extends React.Component {
    constructor(){
        super()
        this.state = {
            username: '',
            password: '',
            monthlyIncome: '',
            availableFunds: ''
        }
        this.handleOnChange = this.handleOnChange.bind(this)
    }

    handleOnChange(event){
        const { name, value} = event.target
        
        this.setState({
            [name]: value
        })
    }

    render() {
        return (
            <Form>
                <Form.Group controlId="registerUsername">
                    <Form.Label></Form.Label>
                    <Form.Control type="text" placeholder="Username" name="username" onChange={this.handleOnChange}/>
                </Form.Group>
                <Form.Group controlId="registerPassword">
                    <Form.Label></Form.Label>
                    <Form.Control type="text" placeholder="Password" name="password" onChange={this.handleOnChange}/>
                </Form.Group>
                <Form.Group controlId="registerMonthlyIncome">
                    <Form.Label></Form.Label>
                    <Form.Control type="text" placeholder="Monthly Income" name="monthlyIncome" onChange={this.handleOnChange}/>
                </Form.Group>
                <Form.Group controlId="registerAvailableFunds">
                    <Form.Label></Form.Label>
                    <Form.Control type="phone" placeholder="Available Funds" name="availableFunds" onChange={this.handleOnChange} />
                </Form.Group>
                <Button variant="primary" className="btn-primary" type="submit">
                    Register
                </Button>
                <a id="registerButton" href="http://localhost:3000/login" target="_self">
                    <Button variant="primary" className="btn-primary">
                        Login
                    </Button>
                </a>
            </Form>
        )
    }
}

export default RegisterForm