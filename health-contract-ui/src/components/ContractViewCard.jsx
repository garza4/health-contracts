import { Accordion } from "react-bootstrap";

const ContractViewCard = ({cardTitle, bodyText, entryType, ...props}) => {

    return(
        <div>
            <Card style={{ width: '18rem' }}>
                <Card.Body>
                    <Card.Title>{cardTitle}</Card.Title>
                    <Card.Text>
                        {bodyText}
                    </Card.Text>
                    <Accordion defaultActiveKey={0}>
                        <ContractViewCard entryType={entryType} data={{}}/>
                    </Accordion>
                    <Button variant="primary">Go somewhere</Button>
                </Card.Body>
            </Card>
        </div>
    )
}

export default ContractViewCard;