import { useState } from 'react';
import {UseAxios} from '../hooks/useAxios';
import * as constants from '../common/constants';
import { Form,Button } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import React from "react";
import ContractViewCard from '../components/ContractViewCard';
import axios from 'axios';
import api from '../common/axios';
const defaultLoginState = {
    email:"",
    password:"",
    response:""
}
const Login = () => {
    const [loginState,setLoginState] = useState(defaultLoginState);
    const {send,loading} = UseAxios();
    const history = useNavigate();

    const handleInput = (e,email) => {
        if(email ==='email'){
            setLoginState({...loginState,email:e.target.value})
        }else{
            setLoginState({...loginState,password:e.target.value})
        }
    }

    const applicationLogin = async () => {
        const authPayload = {
            uid:loginState.email,
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
        
          await api.post(options.url,
            options.data).then( (response) =>{
            if(response && response.status === 200){
                console.log(response);
                history(constants.URI.landingPage);
            }
        }).catch((error) =>{
            console.log(error);
        });
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