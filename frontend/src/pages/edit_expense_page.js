import React from 'react'
import EditExpenseModal from '../components/EditExpenseModal'

function EditExpense() {
    return (
        <div id="edit_expense_page" className="App-page">
                <h2 id="expenseHeader">
                    Edit Expense
                    <EditExpenseModal></EditExpenseModal>
                </h2>
            </div>
    )
}

export default EditExpense