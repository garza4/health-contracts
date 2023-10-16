import { Accordion, Button, Card } from "react-bootstrap";
import AddLogModal from "./AddLogModal";
import { useEffect, useState } from "react";
import ContractEntry from "./ContractEntry";

const ContractViewCard = ({cardTitle, bodyText, entryType,data, ...props}) => {
    const [modalLogState,setModalLogState] = useState({
        firstName:"",
        lastName:"",
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
                    <Accordion defaultActiveKey={0}>
                        <ContractEntry entryType={entryType} data={{}}/>
                    </Accordion>
                    <Button onClick={() => setOpenModal(!openModal)} variant="primary">Log Request</Button>
                </Card.Body>
            </Card>
            {openModal &&
                <AddLogModal info={modalLogState} saveLog={setModalLogState} open={openModal} setOpen={setOpenModal}/>
            }
        </div>
    )
}

export default ContractViewCard;