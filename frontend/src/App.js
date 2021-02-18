import React, {Component} from 'react'
import './App.css';

import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Switch, Route} from "react-router-dom"

import RegisterPage from "./pages/UserRegisterPage";
import LoginPage from './pages/UserLoginPage'
import HomePage from './pages/HomePage';
import EditIncome from './pages/UserEditIncomePage';

import { instanceOf } from 'prop-types';
import { CookiesProvider, withCookies, Cookies } from 'react-cookie';
import ExpenseEditPage from "./pages/ExpenseEditPage";
import ExpenseAllocatePage from "./pages/ExpenseAllocatePage";

class App extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

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
                  <Route path='/expenses/:id/allocate' render = {this.renderExpenseAllocate} />
                </Switch>
              </main>
            </div>
          </BrowserRouter>
        </CookiesProvider>
      </>
    )
  }

  renderEditExpense(routerProps) {
    return <ExpenseEditPage expenseId={routerProps.match.params.id}/>
  }

  renderExpenseAllocate(routerProps) {
    return <ExpenseAllocatePage expenseId={routerProps.match.params.id}/>
  }
}

export default withCookies(App);
