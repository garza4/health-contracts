import { Col, Row } from "react-bootstrap";
import ContractViewCard from "../components/ContractViewCard";
import React, { useEffect, useState } from "react";
import api from '../common/axios';
import { UseAxios } from "../hooks/useAxios";
import { COMPLETED, PENDING } from "../common/constants";


const Home = () => {
    const [reqState,setReqState] = useState({
        pendingReqs:{},
        completedReqs:{}
    });
    const {send,loading} = UseAxios();
    // useEffect(()=> {
    //     let pendingReqs;
    //     let completed;
    //     const getData = async() => {
    //         await api.post(options.url,options.data).then( (response) =>{
    //             if(response && response.status === 200){
    //                 pendingReqs = response;
    //             }
    //             }).catch((error) =>{
    //                 console.log(error);
    //         });

    //         await api.post(options.url,options.data).then( (response) =>{
    //             if(response && response.status === 200){
    //                 completed = response;
    //             }
    //             }).catch((error) =>{
    //                 console.log(error);
    //         });
    //     }
    //     getData();
    //     setReqState({...reqState,pendingReqs:pendingReqs.data,completedReqs:completed.data});
    // },[]);

    return(
        <React.Fragment>
        <div className="homePage">
            <Row>
                <Col>
                    <ContractViewCard cardTitle={"first card"} bodyText={"some text"} entryType={PENDING}/>               
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