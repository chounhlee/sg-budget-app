import React, {Component} from 'react'
import {Table, Button} from 'react-bootstrap'
import {Link} from 'react-router-dom';



const ExpenseTableHeader = () => {
    return(
        <tr>
        
        <th>Expenses</th>
            <th>Amount</th>
            <th>Allocated</th>
            <th>Remaining</th>
            <th>Recurring</th>
            <th></th>
            <th></th>
            <th></th>
            
        </tr>
    );
}

const ExpenseTableRow = ({info}) => {
    return(
        
        <tr>
        <td>{info.expenses}</td>
        <td>{info.amount}</td>
        <td>{info.allocated}</td>
        <td>{info.remaining}</td>
        <td>{info.recurring}</td>
        <td><Link to='/components/EditExpenseModal'><Button name='edt'>Edit</Button></Link></td>
        <td><Button>Delete</Button></td>
        <td><Button>Allocate</Button></td>
        </tr>
        
    );
}


class ExpenseTable extends Component{
    
    
    static defaultProps = {
        infos: [
            {
                
                "expenses": "Rent",
                "amount": "",
                "allocated": "",
                "remaining": "",
                "recurring": ""
            },
            {
               
              "expenses": "Transportation",
              "amount": "",
                "allocated": "",
                "remaining": "",
                "recurring": ""
            },
            {
               
              "expenses": "Student Loan",
                "amount": "",
                "allocated": "",
                "remaining": "",
                "recurring": ""
          },
          {
               
            "expenses": "Summer Vacation",
            "amount": "",
            "allocated": "",
            "remaining": "",
            "recurring": ""
        },
        {
               
          "expenses": "Credit Card",
          "amount": "",
          "allocated": "",
          "remaining": "",
          "recurring": ""
      },
      {
               
        "expenses": "Hospital Bills",
        "amount": "",
        "allocated": "",
        "remaining": "",
        "recurring": ""
    },
        ]
    }
    render() {
      

      const infos = this.props.infos;

        return (
        <Table striped bordered responsive>
            <thead>
                <ExpenseTableHeader/>
            </thead>
            <tbody>
            {infos.map((info, i) => {
                
                return <ExpenseTableRow key={i}
                                        info={info}
                                         />
                    
                
        })}

            </tbody>
            
            </Table>
        
        
        )
    
    
    }

    
}
    

export default ExpenseTable