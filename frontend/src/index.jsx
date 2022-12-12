import React from 'react';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import {createRoot} from "react-dom";
import Error from "./Error/Error";
import Header from "./Header/Header";
import NaviBar from "./NaviBar/NaviBar";

const router = createBrowserRouter([
    // { path: "/", element: <Login /> },
    // { path: "main", element: <Main /> },
    { path: "*", element: <Error />}
]);
createRoot(document.querySelector('#root')).render(
    <div className="content-frame">
        <NaviBar/>
        <Header />
        {/*<Description />*/}
        <RouterProvider router={router}/>
    </div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
