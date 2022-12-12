import React, {useEffect} from "react";
import {Container, Form, Button} from "react-bootstrap";
import './login.css';

export function Login() {
    return (
        <Container id="main-container" className="d-grid h-100">
            <Form id="sign-in-form" className="text-center w-100">
                <img className="mt-5 mb-4 login-logo"
                     src="/resources/img/login.jpg"
                     alt="login-logo"/>
                <h1 className="mb-3 fs-3 fw-normal">Please sign in</h1>
                <Form.Group controlId="sign-in-email-address">
                    <Form.Control type="email" size="lg" placeholder="Email address" className="position-relative"/>
                </Form.Group>
                <Form.Group controlId="sign-in-password">
                    <Form.Control type="password" size="lg" placeholder="Password" className="position-relative"/>
                </Form.Group>
                <div className="d-grid">
                    <Button className="mt-3" variant="primary" size="lg">Sign in</Button>
                    <Button className="mt-3" variant="primary" size="lg">Register</Button>
                </div>
            </Form>
        </Container>
    )
}