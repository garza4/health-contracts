import { useState } from "react"
import { Button, Form, Modal } from "react-bootstrap"
import { API, PENDING } from "../common/constants";
import api from "../common/axios";

const AddLogModal = ({info,setUserInfo,open,setOpen,entryType,...props}) =>{
    const [inSave,setInSave] = useState({saved:false,saveText:"Save",editText:"Edit"});
    const handleSave = () => {
        let saveState = inSave.saved;
        setInSave({...inSave,saved:!saveState});
    }

    const handleApply =() => {
        const request = {
            "uid":info.uid,
            "comments":info.additionalComments,
            "service":info.service,
            "req_funds":info.costOFVisit     
        }
        api.put(API.SAVE_VISIT_TO_SYSTEM,)
    }
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
                        <Form.Label>User ID</Form.Label>
                        <Form.Control disabled={inSave.saved} placeholder="User ID" onChange={e => setUserInfo({...info,uid:e.target.value})} value={info.uid}/>
                        <Form.Label>Service Received</Form.Label>
                        <Form.Control disabled={inSave.saved} placeholder="service" onChange={e => setUserInfo({...info,service:e.target.value})} value={info.service}/>
                        <Form.Label>Amount to fund</Form.Label>
                        <Form.Control disabled={inSave.saved} placeholder="$ToRequest" onChange={e => setUserInfo({...info,costOfVisit:e.target.value})} value={info.costOfVisit}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Enter comments for visit</Form.Label>
                        <Form.Control disabled={inSave.saved} as="textarea" rows={3} onChange={e => setUserInfo({...info,additionalComments:e.target.value})} value={info.additionalComments}/>
                    </Form.Group>
                </Form>
            </Modal.Body>
    
            <Modal.Footer>
                <Button variant="secondary" onClick={()=> setOpen(!open)}>Close</Button>
                <Button variant={!inSave.saved?"primary":"outline-danger"} onClick={(e) =>handleSave(e)}>{!inSave.saved?inSave.saveText:inSave.editText}</Button>
                {entryType===PENDING? <Button onClick={(e)=>handleApply(e)} variant="success">Apply</Button>:null}
                
            </Modal.Footer>
            </Modal.Dialog>
        </div>
    )
}
export default AddLogModal;