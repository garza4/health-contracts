import { Col, Row } from "react-bootstrap";
import ContractViewCard from "../components/ContractViewCard";
import React, { useEffect, useState } from "react";
import api from '../common/axios';
import { UseAxios } from "../hooks/useAxios";
import { API, COMPLETED, PENDING, REQUEST } from "../common/constants";
import {useLocation} from 'react-router-dom';
import AccountInfo from "../components/AccountInfo";
import './home.scss';



const Home = ({uid,...props}) => {
    const location = useLocation();
    const [reqState,setReqState] = useState({
        pendingReqs:[],
        completedReqs:[],
        accountInfo:""
    });
    useEffect(()=> {
        const getData = async() => {
            let pendingData;
            let balanceInfo;
            await api.get(API.GET_VISITS+location.state.uid).then( (response) =>{
                if(response && response.status === 200){
                    pendingData = response.data;
                }
                }).catch((error) =>{
                    console.log(error);
            });

            let visitLog = pendingData.visitationLog;
            let completedReq = pendingData.completedReqs;

            await api.get(API.GET_BALANCE).then((response) => {
                balanceInfo = response.data;
            })
            setReqState({accountInfo:balanceInfo,
                pendingReqs:visitLog,
                completedReqs:completedReq
            });
        }
        getData();
    },[]);

    const completedRequests = [
        
        {"visitRef": 12,
        "uid": "al_eve_big_inc",
        "comments": "",
        "service": "walk",
        "requestedFunds": "10",
        "upkid": 100000,
        "status": "P",
        "provider": "dog provider"}
    ];
    

    return(
        <div className='homePage'>
            <Row>
                <AccountInfo publicAccountNumber={""} balance={reqState.accountInfo.balances?reqState.accountInfo.balances[0].balance:""}/>
            </Row>
            <Row>
                <Col>
                    <ContractViewCard cardTitle={"Log Visit"} bodyText={"Visitations"} entryType={PENDING} data={reqState.pendingReqs} setData={setReqState} uid={uid}/>               
                </Col>
                <Col>
                    <ContractViewCard cardTitle={"Request Funds"} bodyText={"Receive Funds for visitations"} entryType={REQUEST} data={reqState.pendingReqs} setData={setReqState} uid={uid}/>
                </Col>
                <Col>
                    <ContractViewCard cardTitle={"Completed Transactions"} bodyText={"These Funds have been added to your account"} entryType={COMPLETED} data={completedRequests} setData={setReqState} uid={uid}/>
                </Col>
            </Row>
        </div>
    )
}
export default Home;