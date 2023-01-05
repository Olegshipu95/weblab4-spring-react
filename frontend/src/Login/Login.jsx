import React, {useEffect, useState} from "react";
import {Container, Form, Button} from "react-bootstrap";
import './login.css';
import {useNavigate} from "react-router-dom";
import {sendLoginInfo, sendRegisterInfo, sendToServer, validateEmail, validatePass} from "./login";

export function Login() {
    const login = localStorage.getItem("login")
    const navigate = useNavigate();
    useEffect(() => {
        if (login != null) navigate("/main")
    })
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const sign_in = async (e) => {
        e.preventDefault()
        if (!validateEmail(username)) {
            alert("Ваш ник должен быть больше 4")
            return
        }
        if (!validatePass(password)) {
            alert("Ваш пароль должен быть больше 4")
            return
        }
        sendLoginInfo(username, password, setMessage, (result) => {
            // localStorage.setItem("token", result.token)
            localStorage.setItem("login", username)
            navigate("/main")
        })
    }

    const register_in = async (e) => {
        e.preventDefault()
        if (!validateEmail(username)) {
            alert("Ваш ник должен быть больше 4")
            return
        }
        if (!validatePass(password)) {
            alert("Ваш пароль должен быть больше 4")
            return
        }
        sendRegisterInfo(username, password, setMessage, (result) => {
            navigate("/main")
        })
    }

    return (
        <Container id="main-container" className="d-grid h-100">
            <Form id="sign-in-form" className="text-center w-100">
                <img className="mt-5 mb-4 login-logo"
                     src="/resources/img/login.jpg"
                     alt="login-logo"/>
                <h1 className="mb-3 fs-3 fw-normal">Please sign in</h1>
                <Form.Group controlId="sign-in-email-address">
                    <Form.Control id="email" type="email" size="lg" placeholder="Email address"
                                  className="position-relative" onChange={e => setUsername(e.target.value)}/>
                </Form.Group>
                <Form.Group controlId="sign-in-password">
                    <Form.Control id="password" type="password" size="lg" placeholder="Password"
                                  className="position-relative" onChange={e => setPassword(e.target.value)}/>
                </Form.Group>
                <div className="d-grid">
                    <Button className="mt-3" variant="primary" size="lg" onClick={sign_in}>Sign in</Button>
                    <Button className="mt-3" variant="primary" size="lg" onClick={register_in}>Register</Button>
                </div>
            </Form>
            <div>{message}</div>
        </Container>
    )
}