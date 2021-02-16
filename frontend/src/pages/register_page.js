import React from "react"
import "../styles/register_page.css"
import RegisterForm from "../components/RegisterForm"


function RegisterPage() {
  return (
    <div id="register_page" className="App-page">
      <h2 id="header">
        Register
        <RegisterForm></RegisterForm>
      </h2>
    </div>


  )
}

export default RegisterPage