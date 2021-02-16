import React, {Component} from 'react'
import './App.css';
import {Navbar, Nav, Button, Table, Form} from 'react-bootstrap'
import ExpenseTable from './components/ExpenseTable'
import AddExpenseForm from './components/AddExpenseForm'


import { Container, Row, Col } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from 'react-router-dom';


class App extends Component {
  constructor(){
  super();

  this.state = {
    expenses: '',
    amount: '',
    recurring: '',
    infos: []
  }
};

handleFormSubmit = (e) => {
  e.preventDefault();

  let infos = [...this.state.infos];

  infos.push({
    expenses: this.state.expenses,
    amount: this.state.amount,
    recurring: this.state.recurring
  });

  this.setState({
    infos,
    expenses: '',
    amount: '',
    recurring:''
  });
};

handleInputChange = (e) => {
  let input = e.target;
  let name = e.target.name;
  let value = input.value;

  this.setState({
    [name]: value
  })
};


render() {
  return (
    <BrowserRouter>
    <Container fluid> 
     <Navbar id="nav" bg="dark" variant="dark" >Budget App
     <Nav className="links">
     <Nav.Link href="#home">Username</Nav.Link>
      <Nav.Link href="#home">Home</Nav.Link>
      <Nav.Link href="#login">Login</Nav.Link>
      <Nav.Link href="#logout">Logout</Nav.Link>
    </Nav>
      </Navbar>
    <Row>
      <Col>
        <h1 className="text-center">Budget Application</h1>
        <h3>February 2020</h3>
        <h3>Monthly Income:xxx
          <Button id="edit">Edit</Button>
        </h3>
        <h3>Available Fund:xxx</h3>
      </Col>
    </Row>
    <hr />
    <Row>
      <Col sm={8}>
        <h2>My Expense Info</h2>
        <ExpenseTable />
      </Col>
      <Col sm={4}>
        <h2>Add New Expense</h2>
        <AddExpenseForm />
        
      </Col>
      <div className="App">
      <Table infos={ this.state.infos }/>
      <Form handleFormSubmit={ this.handleFormSubmit } 
        handleInputChange={ this.handleInputChange }
        newExpenseName={ this.state.expenses }
        newAmount={ this.state.amount }
        newRecurring={ this.state.recurring } />
        
    </div>
    </Row>

    </Container>
    </BrowserRouter>
);
  }
}

export default App;
