import React, { useEffect } from "react";
import './App.css';
import Login from './pages/login';
import ReactDOM from "react-dom";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route exact path='/' element={<Login/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

ReactDOM.render(
  <App />,
  document.getElementById("root")
);
export default App;
