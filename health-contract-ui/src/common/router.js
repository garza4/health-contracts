import {
    createBrowserRouter,
    RouterProvider
  } from "react-router-dom";
import Login from "../pages/login";
import { createRoot } from "react-dom/client";

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
createRoot(document.getElementById("root")).render(
    <RouterProvider router={router} />
);
export default Router;