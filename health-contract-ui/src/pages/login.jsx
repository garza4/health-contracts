import { useState } from 'react';
import UseAxios from '../hooks/useAxios';
import * as constants from '../common/constants';
import { Form,Button } from 'bootstrap';
const defaultLoginState = {
    email:"",
    password:"",
    response:""
}
const login = () => {
    const [loginState,setLoginState] = useState(defaultLoginState);

    const applicationLogin = () => {
        const authPayload = {
            uid:loginState.email,
            password:loginState.password
        }
        const {data,error,loaded} = UseAxios(constants.URI,"Post",authPayload)
        const returnedData = JSON.stringify(data || {});
        if(data){
            setLoginState({...loginState,response:returnedData});
        }
        
    }

    return(
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
        
    )
}
export default login;