import { useState } from 'react';
import {UseAxios} from '../hooks/useAxios';
import * as constants from '../common/constants';
import { Form,Button, Col, Row } from 'react-bootstrap';
import React from "react";
import Modal from 'react-bootstrap/Modal';
import api from '../common/axios';
import { Navigate, useNavigate } from "react-router-dom";
import './login.scss';

const defaultLoginState = {
    uName:"",
    password:"",
    response:""
}
const Login = () => {
    const [loginState,setLoginState] = useState(defaultLoginState);
    const [modalState,setModalState] = useState({modalEmail:"",modalPassword:"",orgName:"",firstName:"",lastName:"",show:false});
    const navigate = useNavigate();

    const handleInput = (e,inp) => {
        if(inp ==='user'){
            setLoginState({...loginState,uName:e.target.value})
        }else{
            setLoginState({...loginState,password:e.target.value})
        }
    }

    const applicationLogin = async (e) => {
        e.preventDefault();
        const authPayload = {
            uid:loginState.uName,
            password:loginState.password
        }
        const options = {
            method: "post",
            url: "/auth/authenticate",
            data: authPayload,
            timeout:600000,
            headers: {
                'Content-Type': 'application/json',
                'Accept': '*/*',
                'Connection':'keep-alive'
            }
          };
        
          await api.post(options.url,options.data).then( (response) =>{
            if(response && response.status === 200){
                navigate(constants.URI.landingPage, { replace: true, state: {uid:loginState.uName} });
                // setLoginState({...loginState,response:response.status});
            }
            }).catch((error) =>{
                console.log(error);
            });
    }

    const registerUser = async() => {
        const payload = {
            user_name:modalState.modalEmail, 
            password:modalState.modalPassword,
            org_name:modalState.orgName,
            first_name:modalState.firstName,
            last_name:modalState.lastName
        };

        const options = {
            method: "put",
            url: "/account/register",
            data: payload,
            timeout:600000,
            headers: {
                'Content-Type': 'application/json',
                'Accept': '*/*',
                'Connection':'keep-alive'
            }
        };

        await api.post(options.url,options.data).then( (response) =>{
            if(response && response.status === 200){
                setModalState({...modalState,show:false});
                navigate(constants.URI.landingPage, { replace: true, state: {uid:modalState.modalEmail} });
            }
            }).catch((error) =>{
                console.log(error);
            });
    }

    return(
        <div class="homePage">
            <Modal show={modalState.show} onHide={e => setModalState({...modalState,show:!modalState.show})} autoFocus={true} centered={true}>
                <Modal.Header closeButton>
                    <Modal.Title >Register as admin for organization</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group onChange={e => setModalState({...modalState,modalEmail: e.target.value})}>
                            <Form.Label>
                                Email Address
                            </Form.Label>
                            <Form.Control type="email" placeholder='email address' autoFocus/>    
                        </Form.Group>
                        <Form.Group onChange={e => setModalState({...modalState,modalPassword: e.target.value})}>
                            <Form.Label>
                                Password
                            </Form.Label>
                            <Form.Control type="password" placeholder='*********' autoFocus/>    
                        </Form.Group>
                        <Form.Group onChange={e => setModalState({...modalState,orgName: e.target.value})}>
                            <Form.Label>
                                Organization
                            </Form.Label>
                            <Form.Control placeholder='Org Name' autoFocus/>    
                        </Form.Group>
                        <Form.Group onChange={e => setModalState({...modalState,firstName: e.target.value})}>
                            <Form.Label>
                                First Name
                            </Form.Label>
                            <Form.Control placeholder='First Name' autoFocus/>    
                        </Form.Group>
                        <Form.Group onChange={e => setModalState({...modalState,lastName: e.target.value})}>
                            <Form.Label>
                                Last Name
                            </Form.Label>
                            <Form.Control placeholder='Last Name' autoFocus/>    
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={e => setModalState({...modalState,show:false})}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={e => registerUser}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
            <Row>
                <Col>
                    <Form onSubmit={(e)=>applicationLogin(e)}>
                        <Form.Group className="mb-3" 
                            onChange={(e)=> handleInput(e,'user')}
                            controlId="exampleForm.ControlInput1">
                            <Form.Label>User ID</Form.Label>
                            <Form.Control />
                        </Form.Group>
                        <Form.Group className="mb-3" 
                        onChange={(e)=> handleInput(e,'password')}
                        controlId="exampleForm.ControlInput1">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="password" />
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                    </Form> 
                </Col>
                <Button onClick={ e => setModalState({...modalState,show:true})} variant="link">Register Here</Button>
            </Row>
        </div> 
    )
}
export default Login;