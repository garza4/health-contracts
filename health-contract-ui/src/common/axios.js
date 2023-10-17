import axios from 'axios';
const api = axios.create({baseURL:'/'});
api.interceptors.request.use((request) => {
  request.headers['Content-Type'] = 'application/json';
  request.headers['Accept'] = "*/*";
  return request;
},
  (error) => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use((response) => {
    return response;
  },
  (error) => {
    return Promise.reject(error);
  }
);