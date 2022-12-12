import React, {useState} from "react";
import {Navbar, Nav,  Button, Modal, Form, Container} from "react-bootstrap";
import {Link} from "react-router-dom";

export default function NaviBar() {
    const [show, setShow] = useState(false)
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    return (
        <>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" className="p-2">
                <Container>
                    <Navbar.Brand>Weblab4</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                    <Navbar.Collapse id="responsive-navbar-nav" className="justify-content-between">
                        <Nav className="mr-auto">
                            <Nav.Link href="/">Home</Nav.Link>
                            <Nav.Link>Table</Nav.Link>
                            <Nav.Link href="/about">About</Nav.Link>
                            <Nav.Link>Exercise</Nav.Link>
                        </Nav>
                        <Nav>
                            <Button variant="primary" className="me-2" onClick={handleShow}>Log In</Button>
                            <Button variant="primary">Sign out</Button>
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
                            <Form.Control type="email" placeholder="Enter email pls"/>
                        </Form.Group>
                        <Form.Group controlId="fromBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="Password" placeholder="Enter password"/>
                        </Form.Group>
                        <Container className="text-center w-100">
                            <Button variant="primary" className="me-2 mt-2">Log In</Button>
                            <Button variant="primary" className="mt-2" onClick={handleClose}>Close</Button>
                        </Container>
                    </Form>
                </Modal.Body>
            </Modal>
        </>

    )
}