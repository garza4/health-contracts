import { useState } from "react"
import { Button, Form, Modal } from "react-bootstrap"
import { PENDING } from "../common/constants";

const AddLogModal = ({userInfo,setUserInfo,open,setOpen,entryType,...props}) =>{
    const [modalLogState,setModalLogState] = useState({...userInfo});
    return(
        <div
            className="modal show"
            style={{ display: 'block', position: 'initial' }}
        >
            <Modal.Dialog>
            <Modal.Header closeButton onClick={()=>setOpen(!open)}>
                <Modal.Title>Log Request</Modal.Title>
            </Modal.Header>
    
            <Modal.Body>
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>First Name</Form.Label>
                        <Form.Control placeholder="First Name" onChange={e => setModalLogState({...modalLogState,firstName:e.target.value})} value={modalLogState.firstName}/>
                        <Form.Label>Last Name</Form.Label>
                        <Form.Control placeholder="Last Name" onChange={e => setModalLogState({...modalLogState,lastName:e.target.value})} value={modalLogState.lastName}/>
                        <Form.Label>Service Received</Form.Label>
                        <Form.Control placeholder="service" onChange={e => setModalLogState({...modalLogState,service:e.target.value})} value={modalLogState.service}/>
                        <Form.Label>Amount to fund</Form.Label>
                        <Form.Control placeholder="$ToRequest" onChange={e => setModalLogState({...modalLogState,costOfVisit:e.target.value})} value={modalLogState.costOfVisit}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Enter comments for visit</Form.Label>
                        <Form.Control as="textarea" rows={3} onChange={e => setModalLogState({...modalLogState,additionalComments:e.target.value})} value={modalLogState.additionalComments}/>
                    </Form.Group>
                </Form>
            </Modal.Body>
    
            <Modal.Footer>
                <Button variant="secondary" onClick={()=> setOpen(!open)}>Close</Button>
                <Button variant="primary" onClick={() => setUserInfo({...modalLogState})}>Save changes</Button>
                {entryType===PENDING? <Button variant="success">Apply</Button>:null}
                
            </Modal.Footer>
            </Modal.Dialog>
        </div>
    )
}
export default AddLogModal;