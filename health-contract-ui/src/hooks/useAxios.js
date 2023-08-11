import axios from "axios";
import { useCallback, useEffect, useRef, useState } from "react";

export const UseAxios = (url,method,payload) => {
    const [loading, setLoading] = useState(false);
    const [error,setError] = useState(null);
    const controllerRef = useRef(new AbortController());
    const urlTokens = useRef([]);
    const cancel = () => {
        controllerRef.current.abort();
    };


    const send = useCallback(async (url,method,errorMessage=null,body=null,payload,responseType='json',params=null) =>{
        setLoading(true);
        const cancelToken = axios.CancelToken.source();
        const currentReq = urlTokens.current.findIndex(item => item.url === url);
        if(currentReq !== -1){
            if(cancel){
                urlTokens.current[currentReq].token.cancel();
            }
            urlTokens.current.splice(currentReq,1);
        }
        urlTokens.current.push({url,token:cancelToken});
        try{
            const response = await axios.request({
                method,
                url,
                responseType:'json',
                cancelToken: cancelToken.token,
                ...(body && {data:body}),
                ...(params && {params:params})
            });
            return response;

        }catch (e){
            setError(e);
        }
    },[]);

    useEffect(() => {
        urlTokens.current.forEach(item => item.token.cancel());
        urlTokens.current.length = 0;
    }, []);
    return {loading,send,error}
};