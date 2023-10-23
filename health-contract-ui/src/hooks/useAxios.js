
import axios from "axios";
import { useCallback, useEffect, useRef, useState } from "react";

export const UseAxios = () => {
    const [loading, setLoading] = useState(false);
    const [error,setError] = useState(null);
    const controllerRef = useRef(new AbortController());
    const urlTokens = useRef([]);
    const cancel = () => {
        controllerRef.current.abort();
    };


    const send = useCallback(async (url,method='POST',errorMessage=null,body=null,payload,responseType='json',params=null) =>{
        setLoading(true);
        const cancelToken = axios.CancelToken.source();
        removeToken(url);
        urlTokens.current.push({url,token: cancelToken});
        try{
            const response = await axios.request({
                method,
                url,
                responseType:'application/json',
                cancelToken: cancelToken.token,
                ...(body && {data:body}),
                ...(params && {params:params})
            });
            setLoading(false);
            setError(false);
            removeToken(url);
            return response;
        }catch (e){
            setError(e);
        }
    },[]);

    const removeToken = (url,cancel=true) => {
        const requestIndex = urlTokens.current.findIndex(item=>item.url===url);
        if(requestIndex !== -1){
            if(cancel){
                urlTokens.current[requestIndex].token.cancel();
            }
            urlTokens.current.splice(requestIndex,1);
        }
    }

    useEffect(() => {
        urlTokens.current.forEach(item => item.token.cancel());
        urlTokens.current.length = 0;
    }, []);

    return {loading,send,error}
};