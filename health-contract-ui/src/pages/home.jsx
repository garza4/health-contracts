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
        completedReqs:{},
        userInfo:{}
    });
    const {send,loading} = UseAxios();
    useEffect(()=> {
        let pendingReqs;
        let completed;
        let userInfo;
        const getData = async() => {
            await api.get(API.GET_USER+location.state.uid).then((resp) => {
                userInfo=resp;
            });

            await api.get(API.GET_VISITS,userInfo.data.provider).then( (response) =>{
                if(response && response.status === 200){
                    pendingReqs = response;
                }
                }).catch((error) =>{
                    console.log(error);
            });
        }
        getData();
        if(userInfo && pendingReqs){
            setReqState({...reqState,pendingReqs:pendingReqs.data,completedReqs:completed.data,userInfo:userInfo.data});
        }
    },[]);

    return(
        <React.Fragment>
        <div className="homePage">
            <Row>
                <Col>
                    <ContractViewCard cardTitle={"Log Visit"} bodyText={"Visitations"} entryType={PENDING}/>               
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