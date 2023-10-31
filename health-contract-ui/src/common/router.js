import {
    createBrowserRouter,
    RouterProvider
  } from "react-router-dom";
import Login from "../pages/login";
import { createRoot } from "react-dom/client";
import Home from "../pages/home";

const Router = createBrowserRouter([
  {
    path: "/",
    element: <Login/>,
  },
  {
    path:"/home",
    element:<Home/>
  }
]);
createRoot(document.getElementById("root")).render(
    <RouterProvider router={Router} />
);
export default Router;