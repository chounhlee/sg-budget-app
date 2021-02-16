import React, {Component} from 'react'
import './App.css';
import {Navbar, Nav, Button, Table, Form} from 'react-bootstrap'
import ExpenseTable from './components/ExpenseTable'
import AddExpenseForm from './components/AddExpenseForm'


import { Container, Row, Col } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Switch, Route } from "react-router-dom"

import './App.css'
import RegisterPage from "./pages/register_page";
import LoginPage from './pages/login_page'
import HomePage from './pages/home_page';
import EditExpense from './pages/edit_expense_page';
import EditIncome from './pages/edit_income_page';
import AllocatePage from './pages/allocate_page';

class App extends Component {
  render() {
      return (
        <BrowserRouter>
          <div className="App">
            <main>
                  <Switch>
                      <Route exact path='/' component={LoginPage} />
                      <Route path='/register' component={RegisterPage} />
                      <Route path='/login' component={LoginPage} />
                      <Route path='/home' component={HomePage} />
                      <Route path='/editExpense' component={EditExpense} />
                      <Route path='/editIncome' component={EditIncome} />
                      <Route path='/allocate' component={AllocatePage} />

                  </Switch>
            </main>
          </div>
        </BrowserRouter>
          
      )
  }
}

export default App;
