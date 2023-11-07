import React from "react";
import './App.css';
import * as ReactDOM from "react-dom/client";
import {BrowserRouter, Route, RouterProvider, Routes}  from "react-router-dom";
import Router from "./common/router";
import { URI } from "./common/constants";
import Login from "./pages/login";
import Home from "./pages/home";

function App(){
  const root = ReactDOM.createRoot(document.getElementById("root"))
  return (
    root.render(
      <BrowserRouter>
        <Routes>
          <Route path={URI.login} element={<Login/>}/>
          <Route path={URI.landingPage} element={<Home />}/>
        </Routes> 
      </BrowserRouter>
    )
  )
}

export default App;
