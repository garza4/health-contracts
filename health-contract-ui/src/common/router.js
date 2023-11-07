import {
    createBrowserRouter,
    createRoutesFromElements,
    Route,
    RouterProvider
  } from "react-router-dom";
import Login from "../pages/login";
import Home from "../pages/home";
import { URI } from "./constants";

const Router = createBrowserRouter(
  createRoutesFromElements(
    <Route path={URI.login} element={<Login/>}>
      <Route exact index path={URI.landingPage} element={<Home />} />
    </Route>
  )
)
export default Router;