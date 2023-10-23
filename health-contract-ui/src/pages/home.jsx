import { Col, Row } from "react-bootstrap";
import ContractViewCard from "../components/ContractViewCard"

const Home = () => {

    return(
        <Row>
            <Col>
                <ContractViewCard cardTitle={"first card"} bodyText={"some text"} entryType={"type1"}/>               
            </Col>
            <Col>
                <ContractViewCard cardTitle={"second card"} bodyText={"some text"} entryType={"type1"}/>
            </Col>
            <Col>
                <ContractViewCard cardTitle={"third card"} bodyText={"some text"} entryType={"type1"}/>
            </Col>
        </Row>
    )
}
export default Home;