import axios from 'axios';
const api = axios.create({baseURL:'/'});
jwtToken ="";
api.interceptors.request.use(
    (config) => {
        if(!axios.getUri.contains("/auth")){
            config.headers.Authorization = jwtToken;
        }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  api.interceptors.response.use(
    (response) => {
        if(axios.getUri.contains("/auth")){
          jwtToken=request.headers.getAuthorization();
        }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  export default api;