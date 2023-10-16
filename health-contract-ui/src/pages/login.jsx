import { useState } from 'react';
import {UseAxios} from '../hooks/useAxios';
import * as constants from '../common/constants';
import { Form,Button } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import axios from "axios";
import React from "react";
import ContractViewCard from '../components/ContractViewCard';
const defaultLoginState = {
    email:"",
    password:"",
    response:""
}
const Login = () => {
    const [loginState,setLoginState] = useState(defaultLoginState);
    const {send,loading} = UseAxios();
    const nav = useNavigate();
    const sending = axios.create();

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
        const options = {
            method: "post",
            url: "/auth/authenticate",
            data: authPayload,
            timeout:6000
          };
        try{
            const resp = await sending(options);
            console.log(resp);
            // nav(constants.URI.landingPage);
        }catch(e){

        }
    }

    return(
        <div>
           <Form onSubmit={applicationLogin}>
                <Form.Group className="mb-3" 
                    onChange={(e)=> handleInput(e,'email')}
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

            {/* uncomment to test view card:  
            <ContractViewCard cardTitle={"first card"} bodyText={"some text"} entryType={"type1"}/> */}
        </div> 
    )
}
export default Login;