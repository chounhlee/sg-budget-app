import React, {Component} from 'react'
import './App.css';
import {Navbar, Nav, Button, Table, Form} from 'react-bootstrap'
import ExpenseTable from './components/ExpenseTable'
import AddExpenseForm from './components/AddExpenseForm'


import {Container, Row, Col} from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Switch, Route} from "react-router-dom"

import './App.css'
import RegisterPage from "./pages/UserRegisterPage";
import LoginPage from './pages/UserLoginPage'
import HomePage from './pages/HomePage';
import EditExpense from './pages/ExpenseEditPage';
import EditIncome from './pages/UserEditIncomePage';
import AllocatePage from './pages/ExpenseAllocatePage';

import { instanceOf } from 'prop-types';
import { CookiesProvider, withCookies, Cookies } from 'react-cookie';

class App extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

  constructor(props) {
    super(props);
  }

  componentDidMount() {

  }

  render() {
    return (
      <>
        <CookiesProvider>
          <BrowserRouter>
            <div className="App">
              <main>
                <Switch>
                  <Route exact path='/' component={LoginPage} />
                  <Route path='/register' component={RegisterPage} />
                  <Route path='/login' component={LoginPage} />
                  <Route path='/home' component={HomePage} />
                  <Route path='/expenses/:id/edit' render = {this.renderEditExpense} />
                  <Route path='/editIncome' component={EditIncome} />
                  <Route path='/allocate' component={AllocatePage} />
                </Switch>
              </main>
            </div>
          </BrowserRouter>
        </CookiesProvider>
      </>
    )
  }

  renderEditExpense(routerProps) {
    return <EditExpense expenseId={routerProps.match.params.id}/>
  }
}

export default withCookies(App);
