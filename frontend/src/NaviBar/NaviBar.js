import React, {useState} from "react";
import {Navbar, Nav,  Button, Modal, Form, Container} from "react-bootstrap";
import {Link, Navigate, useNavigate} from "react-router-dom";
import {sendLoginInfo, validateEmail, validatePass} from "../Login/login";
import "../Login/login"
export default function NaviBar() {
    const login = localStorage.getItem("login")
    const [show, setShow] = useState(false)
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState("");
    const sign_in = async (e) =>{
        e.preventDefault()
        if(!validateEmail(username)){
            alert("Ваш ник должен быть больше 4")
            return
        }
        if(!validatePass(password)){
            alert("Ваш пароль должен быть больше 4")
            return
        }
        sendLoginInfo(username,password,setMessage,(result) => {
            localStorage.setItem("token", result.token)
            localStorage.setItem("login", username)
            window.location.href = "/main"
        })
    }
    const deleteAuth = async () => {
        localStorage.removeItem("token")
        localStorage.removeItem("login")
        window.location.href = "/"
    }
    return (
        <>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" className="p-2">
                <Container>
                    <Navbar.Brand>Weblab4</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                    <Navbar.Collapse id="responsive-navbar-nav" className="justify-content-between">
                        <Nav className="mr-auto">
                            <Nav.Link href="/"></Nav.Link>
                            <Nav.Link href="/main">Table</Nav.Link>
                            <Nav.Link href="/about">About</Nav.Link>
                            <Nav.Link href="/exercise">Exercise</Nav.Link>
                        </Nav>
                        <Nav>
                            {login===undefined||login==null? <Button variant="primary" className="me-2" onClick={handleShow}>Log In</Button> :
                            <Button variant="primary" onClick={deleteAuth}>Sign out</Button>}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Log in</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="fromBasicEmail">
                            <Form.Label>Email Address</Form.Label>
                            <Form.Control type="email" placeholder="Enter email pls" onChange={e => setUsername(e.target.value)}/>
                        </Form.Group>
                        <Form.Group controlId="fromBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="Password" placeholder="Enter password" onChange={e => setPassword(e.target.value)}/>
                        </Form.Group>
                        <Container className="text-center w-100">
                            <Button variant="primary" className="me-2 mt-2" onClick={sign_in}>Log In</Button>
                            <Button variant="primary" className="mt-2" onClick={handleClose}>Close</Button>
                        </Container>
                        <div>{message}</div>
                    </Form>
                </Modal.Body>
            </Modal>
        </>

    )
}