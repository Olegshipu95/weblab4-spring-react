import React from "react";
import {Navbar, Nav, Link, Button} from "react-bootstrap";

export default function NaviBar() {
    return (
        <>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" className="p-2">
                <Navbar.Brand>Weblab4</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                <Navbar.Collapse id="responsive-navbar-nav" className="justify-content-between">
                    <Nav className="mr-auto">
                        <Nav.Link>Home</Nav.Link>
                        <Nav.Link>Table</Nav.Link>
                        <Nav.Link>About</Nav.Link>
                    </Nav>
                    <Nav>
                        <Button variant="primary" className="me-2">Log In</Button>
                        <Button variant="primary">Sign out</Button>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        </>

    )
}