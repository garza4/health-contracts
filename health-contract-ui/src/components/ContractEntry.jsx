import Accordion from "react-bootstrap/Accordion";
import Table from 'react-bootstrap/Table';
import { API, PENDING, REQUEST } from "../common/constants";
import { Button, Form } from "react-bootstrap";
import api from "../common/axios";
import { useEffect, useState } from "react";
import AddLogModal from "./AddLogModal";
import ConfirmSecret from "./ConfirmSecret";

const ContractEntry = ({entryType,data,uid,...props}) => {
    const [openModal,setOpenModal] = useState(false);
    const [amountToFund,setAmountToFund] = useState([]);
    const [visitationData,setVisitationData] = useState({logs:[],secret:"",fundReq:{}});
    useEffect(()=>{
        let tempData  = [];
        if(data){
            data.map((log,i) => {
                tempData.push({entry:log,amountToFund:""});
            });
            setVisitationData({...visitationData,logs:tempData})
        }
    },[data])
    
    const requestFunds = async (i) => {
        setOpenModal(!openModal);
        const request = {
            amount:visitationData.logs[i].amountToFund,
            source_secret:visitationData.secret,
            source_public:"GCKJLDPW6RUGTWCKZ5ZTLHAXU7NGPGELZQHVVNQUKSCSCK5NK7TQE74Z", //should be retrieved from the backend
            admin_user:uid,
            patient:visitationData.logs[i].uid
        }
        setVisitationData({...visitationData,fundReq:request});
    }

    const handleAmountToFundEntry = (e,i) => {
        let tempLogs = {...visitationData};
        tempLogs.logs[i].amountToFund = e.target.value;
        setVisitationData({...tempLogs});
    }
    return(
        <>
            {data ?
                visitationData.logs.map((log,i) => {
                    return(
                        <Accordion.Item eventKey={i}>
                            <Accordion.Header>
                                Patient name: {log.entry.uid?log.entry.uid:""}    Amount: {log.entry.requestedFunds?log.entry.requestedFunds:""}
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
                                            <th>
                                                Service
                                            </th>
                                            <th>
                                                Comment
                                            </th>
                                            {entryType===REQUEST && 
                                                <th>
                                                    Amount to fund
                                                </th>
                                            }
                                        </tr>
                                        
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                {log.entry.uid?log.entry.uid:""}
                                            </td>
                                            <td>
                                                {log.entry.requestedFunds?log.entry.requestedFunds:""}
                                            </td>
                                            <td>
                                                {log.entry.service?log.entry.service:""}
                                            </td>
                                            <td>
                                                {log.entry.comments?log.entry.comments:""}
                                            </td>
                                            {entryType === REQUEST && 
                                                <td>
                                                    <Form.Group className="toFund" controlId="amount.to.fund">
                                                        <Form.Control 
                                                            type="text"
                                                            onChange={e => handleAmountToFundEntry(e,i)} 
                                                            value={visitationData.logs[i].amountToFund}
                                                        />
                                                    </Form.Group>
                                                </td>
                                            }
                                            
                                        </tr>
                                    </tbody>
                                </Table>
                                {entryType===REQUEST && <Button variant="success" onClick={(e)=>requestFunds(i)}>Request Funds</Button>}
                            </Accordion.Body>
                        </Accordion.Item>
                    )})
            :
            <>no data</>}
            {openModal &&
                <ConfirmSecret open={openModal} setOpen={setOpenModal} secret={visitationData} setSecret={setVisitationData} fundReq={visitationData.fundReq}/>
            }
        </>
        
    );

}

export default ContractEntry;