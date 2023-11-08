import { Accordion, Button, Card } from "react-bootstrap";
import AddLogModal from "./AddLogModal";
import { useEffect, useState } from "react";
import ContractEntry from "./ContractEntry";

const ContractViewCard = ({cardTitle, bodyText, entryType,data,uid, ...props}) => {
    const [modalLogState,setModalLogState] = useState({
        uid:uid,
        service:"",
        costOfVisit:"",
        additionalComments:""
    });
    const [openModal,setOpenModal] = useState(false);

    return(
        <div>
            <Card style={{ width: '18rem' }}>
                <Card.Body>
                    <Card.Title>{cardTitle}</Card.Title>
                    <Card.Text>
                        {bodyText}
                    </Card.Text>
                    <Accordion defaultActiveKey="0">
                        <ContractEntry entryType={entryType} data={data}/>
                    </Accordion>
                    <Button onClick={() => setOpenModal(!openModal)} variant="primary">Log Request</Button>
                </Card.Body>
            </Card>
            {openModal &&
                <AddLogModal info={modalLogState} setUserInfo={setModalLogState} saveLog={setModalLogState} open={openModal} setOpen={setOpenModal} entryType={entryType}/>
            }
        </div>
    )
}

export default ContractViewCard;