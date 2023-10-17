import React, { useEffect } from "react";
import './App.css';
import Login from './pages/login';
import * as ReactDOM from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
}  from "react-router-dom";
import Home from "./pages/home";

function App(){
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Login/>,
    },
    {
      path:"/home",
      element:<Home/>
    }
  ]);
  
  
  ReactDOM.createRoot(document.getElementById("root")).render(
    <RouterProvider router={router} />
  );
}

export default App;
