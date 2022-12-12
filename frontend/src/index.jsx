import React from 'react';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import {createRoot} from "react-dom";
import Error from "./Error/Error";
import About from "./About/About";
import NaviBar from "./NaviBar/NaviBar";
import {Login} from "./Login/Login";
import 'bootstrap/dist/css/bootstrap.min.css';

const router = createBrowserRouter([
    { path: "/", element: <Login /> },
    { path: "/about", element: <About />},
    // { path: "main", element: <Main /> },
    { path: "*", element: <Error />}
]);
createRoot(document.querySelector('#root')).render(
    <div className="content-frame">
        <NaviBar/>

        {/*<Exercise />*/}
        <RouterProvider router={router}/>
    </div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
