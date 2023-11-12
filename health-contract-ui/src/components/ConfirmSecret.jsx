import { Button, Form, Modal } from "react-bootstrap";
import api from "../common/axios";
import { API } from "../common/constants";

const ConfirmSecret = ({open,setOpen,setSecret,secret,fundReq,...props}) => {
    const handleSubmit = async (e) => {
        if(fundReq){
            await api.post(API.REQ_FUNDS,JSON.stringify(fundReq)).then((response) => {
                console.log(response);
            });
        }
        setOpen(!open);
    }
    return (
        <div className="modal show"
            style={{ display: 'block', position: 'initial' }}
        >
            <Modal.Dialog>
            <Modal.Header closeButton onClick={()=>setOpen(!open)}>
                <Modal.Title>Confirm Your Secret</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Please enter you secret</Form.Label>
                        <Form.Control as="textarea" rows={3} onChange={e => setSecret({...secret,secret:e.target.value})} value={secret.secret}/>
                    </Form.Group>
                </Form>
            </Modal.Body>

            <Modal.Footer>
                <Button variant="secondary" onClick={()=> setOpen(!open)}>Close</Button>
                <Button onClick={(e)=>handleSubmit(e)} variant="success">Submit</Button>  
            </Modal.Footer>
            </Modal.Dialog>
        </div>
    )
}

export default ConfirmSecret;