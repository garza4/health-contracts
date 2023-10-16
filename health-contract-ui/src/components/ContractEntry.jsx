import Accordion from "react-bootstrap/Accordion";
import Table from 'react-bootstrap/Table';

const ContractEntry = ({entryType,data,...props}) => {

    const constructEntry = (entry) => {
        if(entryType === 'request'){
            return (
                <Accordion.Item>
                    <Accordion.Header>
                        <row>
                            Patient name: {entry.name?entry.name:""}   Date:  {entry.requestDate?entry.requestDate:""}  Amount: {entry.amount?entry.amount:""}
                        </row>
                    </Accordion.Header>
                    <Table striped bordered hover> 
                        <thead>
                            Request ID
                        </thead>
                        <thead>
                            Patient Name
                        </thead>
                        <thead>
                            Cost of Care
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    {entry.id?entry.id:""}
                                </td>
                                <td>
                                    {entry.name?entry.name:""}
                                </td>
                                <td>
                                    {entry.cost?entry.cost:""}
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </Accordion.Item>
            )
        }

    }

    return(
        // data ?
        //     data.map((entry =>{
        //         constructEntry(entry)
        //     })):
        <>no data</>
    );

}

export default ContractEntry;