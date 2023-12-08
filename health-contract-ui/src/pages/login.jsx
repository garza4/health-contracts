import { useState } from 'react';
import {UseAxios} from '../hooks/useAxios';
import * as constants from '../common/constants';
import { Form,Button, Col, Row } from 'react-bootstrap';
import React from "react";
import ContractViewCard from '../components/ContractViewCard';
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
    const {send,loading} = UseAxios();
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

    return(
        <React.Fragment>
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

            {/* uncomment to test view card:  
            <ContractViewCard cardTitle={"first card"} bodyText={"some text"} entryType={"type1"}/> */}
        </Row> 
        </React.Fragment>
    )
}
export default Login;