import React from "react"
import "../styles/register_page.css"
import UserRegisterForm from "../components/UserRegisterForm"


function UserRegisterPage() {
  return (
    <div id="register_page" className="App-page">
      <h2 id="header">
        Register
        <UserRegisterForm></UserRegisterForm>
      </h2>
    </div>
  )
}

export default UserRegisterPage