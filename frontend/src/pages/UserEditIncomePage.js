import React from 'react'
import UserEditIncomeForm from '../components/UserEditIncomeForm'

function UserEditIncomePage() {
  return (
    <div id="edit_income_page" className="App-page">
      <h2 id="incomeHeader">
        Edit Income and Fund
        <UserEditIncomeForm></UserEditIncomeForm>
      </h2>
    </div>
  )
}

export default UserEditIncomePage