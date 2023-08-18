import {
    createBrowserRouter,
    RouterProvider,
    Route,
    Link,
  } from "react-router-dom";
import Login from "../pages/login";
import { createRoot } from "react-dom/client";

const Router = createBrowserRouter([
    {
        path: "/login",
        element: (
            <Login/>
        )
    }
  ])
createRoot(document.getElementById("root")).render(
    <RouterProvider router={Router} />
);
export default Router;