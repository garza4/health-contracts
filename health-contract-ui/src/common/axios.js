import axios from 'axios';
const api = axios.create({baseURL:'http://localhost:8080/api/v1'});
jwtToken ="";
api.interceptors.request.use(
    (config) => {
        if(!axios.getUri.contains("login")){
            config.headers.Authorization = jwtToken;
        }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  api.interceptors.response.use(
    (request) => {
        if(axios.getUri.contains("login")){
            jwtToken=request.headers.getAuthorization();
        }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  export default api;