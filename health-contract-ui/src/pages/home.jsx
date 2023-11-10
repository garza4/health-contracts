import { Col, Row } from "react-bootstrap";
import ContractViewCard from "../components/ContractViewCard";
import React, { useEffect, useState } from "react";
import api from '../common/axios';
import { UseAxios } from "../hooks/useAxios";
import { API, COMPLETED, PENDING } from "../common/constants";
import {useLocation} from 'react-router-dom';



const Home = ({uid,...props}) => {
    const location = useLocation();
    const [reqState,setReqState] = useState({
        pendingReqs:{},
        completedReqs:{}
    });
    useEffect(()=> {
        const getData = async() => {

            await api.get(API.GET_VISITS+location.state.uid).then( (response) =>{
                if(response && response.status === 200){
                    setReqState({...reqState,pendingReqs:response.data});
                }
                }).catch((error) =>{
                    console.log(error);
            });

            await api.get('/account/balance').then((response) => {
                console.log(response);
            })
        }
        getData();
    },[]);

    return(
        <React.Fragment>
        <div className="homePage">
            <Row>
                <Col>
                    <ContractViewCard cardTitle={"Log Visit"} bodyText={"Visitations"} entryType={PENDING} data={reqState.pendingReqs} setData={setReqState} uid={uid}/>               
                </Col>
                <Col>
                    <ContractViewCard cardTitle={"second card"} bodyText={"some text"} entryType={COMPLETED}/>
                </Col>
                <Col>
                    <ContractViewCard cardTitle={"third card"} bodyText={"some text"} entryType={"type1"}/>
                </Col>
            </Row>
        </div>
        </React.Fragment>
    )
}
export default Home;