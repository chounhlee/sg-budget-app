import React from 'react'
import "../styles/login_page.css"
import UserLoginForm from '../components/UserLoginForm'

function LoginPage() {
  return (
    <div id="login_page" className="App-page">
      <h2 id="loginHeader">
        Login
        <UserLoginForm></UserLoginForm>
      </h2>
    </div>
  )
}

export default LoginPage