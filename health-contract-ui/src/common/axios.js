import axios from 'axios';
const api = axios.create();
api.defaults.headers.common['Content-Type'] = 'application/json';
api.defaults.baseURL = '/';
api.interceptors.request.use((request) => {
  console.log(request);
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
export default api;