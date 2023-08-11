import { useState } from 'react';
import {UseAxios} from '../hooks/useAxios';
import * as constants from '../common/constants';
import { Form,Button } from 'react-bootstrap';
const defaultLoginState = {
    email:"",
    password:"",
    response:""
}
const Login = () => {
    const [loginState,setLoginState] = useState(defaultLoginState);
    const {send,loading} = UseAxios();

    const applicationLogin = () => {
        const authPayload = {
            uid:loginState.email,
            password:loginState.password
        }
        const data = send(constants.URI.login,"Post",authPayload);
        const returnedData = JSON.stringify(data || {});
        if(data){
            setLoginState({...loginState,response:returnedData});
        }
        
    }

    return(
        <div>
           <Form onSubmit={() => applicationLogin()}>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="name@example.com" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
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