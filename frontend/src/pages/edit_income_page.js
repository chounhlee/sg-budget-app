import React from 'react'
import EditIncomeModal from '../components/EditIncomeModal'

function EditIncome() {
    return (
        <div id="edit_income_page" className="App-page">
                <h2 id="incomeHeader">
                    Edit Income and Fund
                    <EditIncomeModal></EditIncomeModal>
                </h2>
            </div>
    )
}

export default EditIncome