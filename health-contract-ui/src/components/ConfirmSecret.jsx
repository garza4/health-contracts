import { Button, Form, Modal } from "react-bootstrap";

const ConfirmSecret = ({open,setOpen,setSecret,secret,...props}) => {
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
                <Button onClick={(e)=>setOpen(!open)} variant="success">Done</Button>  
            </Modal.Footer>
            </Modal.Dialog>
        </div>
    )
}

export default ConfirmSecret;