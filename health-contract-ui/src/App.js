import React from "react";
import './App.css';
import * as ReactDOM from "react-dom/client";
import {RouterProvider}  from "react-router-dom";
import Router from "./common/router";

function App(){
  const root = ReactDOM.createRoot(document.getElementById("root"))
  .render(<RouterProvider router={Router} />);
}

export default App;
