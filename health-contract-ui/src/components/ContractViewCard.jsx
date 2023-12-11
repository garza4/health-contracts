import { Accordion, Button, Card, Col, Row } from "react-bootstrap";
import AddLogModal from "./AddLogModal";
import { useEffect, useState } from "react";
import ContractEntry from "./ContractEntry";
import { PENDING, REQUEST } from "../common/constants";

const ContractViewCard = ({cardTitle, bodyText, entryType,data,setData,uid, ...props}) => {
    const [modalLogState,setModalLogState] = useState({
        uid:uid,
        service:"",
        costOfVisit:"",
        additionalComments:""
    });
    const [openModal,setOpenModal] = useState(false);
    return(
        <Col>
            <Card style={{ width: '30rem' }}>
                <Card.Body>
                    <Card.Title>{cardTitle}</Card.Title>
                    <Card.Text>
                        {bodyText}
                    </Card.Text>
                    <Accordion defaultActiveKey="0">
                        <ContractEntry entryType={entryType} data={data}/>
                    </Accordion>
                    {entryType===PENDING && <Button onClick={() => setOpenModal(!openModal)} variant="primary">Log Request</Button>}
                </Card.Body>
            </Card>
            {openModal &&
                <AddLogModal 
                    info={modalLogState} 
                    setUserInfo={setModalLogState} 
                    saveLog={setModalLogState} 
                    open={openModal} 
                    setOpen={setOpenModal} 
                    entryType={entryType}
                    data={data}
                    setData={setData}/>
            }
        </Col>
    )
}

export default ContractViewCard;