import React from "react"
import "../styles/register_page.css"
import RegisterForm from "../components/RegisterForm"


function UserRegisterPage() {
  return (
    <div id="register_page" className="App-page">
      <h2 id="header">
        Register
        <RegisterForm></RegisterForm>
      </h2>
    </div>


  )
}

export default UserRegisterPage