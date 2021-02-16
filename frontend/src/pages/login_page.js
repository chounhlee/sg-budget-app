import React from 'react'
import "../styles/login_page.css"
import LoginForm from '../components/LoginForm'

function LoginPage() {
    return (
        <div id="login_page" className="App-page">
                <h2 id="loginHeader">
                    Login
                    <LoginForm></LoginForm>
                </h2>
            </div>
    )
}

export default LoginPage