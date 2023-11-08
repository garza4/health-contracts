import Accordion from "react-bootstrap/Accordion";
import Table from 'react-bootstrap/Table';
import { PENDING } from "../common/constants";

const ContractEntry = ({entryType,data,uid,...props}) => {


    return(
        data && data.visitations ?
            data.visitations.map((log,i) => {
                return(
                    <Accordion.Item eventKey={i}>
                        <Accordion.Header>
                            Patient name: {log.uid?log.uid:""}    Amount: {log.requestedFunds?log.requestedFunds:""}
                        </Accordion.Header>
                        <Accordion.Body>
                            <Table striped bordered hover> 
                                <thead>
                                    <tr>
                                        <th>
                                            Request ID
                                        </th>
                                        <th>
                                            Cost of Care
                                        </th>
                                    </tr>
                                    
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            {log.uid?log.uid:""}
                                        </td>
                                        <td>
                                            {log.requestedFunds?log.requestedFunds:""}
                                        </td>
                                    </tr>
                                </tbody>
                            </Table>
                        </Accordion.Body>
                    </Accordion.Item>
                )})
        :
        <>no data</>
    );

}

export default ContractEntry;