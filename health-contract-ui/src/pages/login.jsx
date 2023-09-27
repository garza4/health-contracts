import { useState } from 'react';
import {UseAxios} from '../hooks/useAxios';
import * as constants from '../common/constants';
import { Form,Button } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import React from "react";
const defaultLoginState = {
    email:"",
    password:"",
    response:""
}
const Login = () => {
    const [loginState,setLoginState] = useState(defaultLoginState);
    const {send,loading} = UseAxios();
    const nav = useNavigate();

    const handleInput = (e,email) => {
        if(email ==='email'){
            setLoginState({...loginState,email:e.target.value})
        }else{
            setLoginState({...loginState,password:e.target.value})
        }
    }

    const applicationLogin = async () => {
        console.log("loggin in to application");
        const authPayload = {
            uid:loginState.email,
            password:loginState.password
        }
        const data = await send('http://localhost:8080/authenticate',"Post",authPayload);
        const returnedData = JSON.stringify(data || {});
        if(data){
            setLoginState({...loginState,response:returnedData});
        }
        nav(constants.URI.landingPage);
        
    }

    return(
        <div>
           <Form onSubmit={applicationLogin}>
                <Form.Group className="mb-3" 
                    onChange={(e)=> handleInput(e,'email')}
                    controlId="exampleForm.ControlInput1">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="name@example.com" />
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
        </div> 
    )
}
export default Login;