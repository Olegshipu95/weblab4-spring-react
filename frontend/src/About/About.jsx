import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './about.css';
import {Col, Container, Row} from "react-bootstrap";
function About() {
    return (
        <>
            <Container id="main-container" className="d-grid h-100">
                <div className="text-center w-100">
                    <img className="mt-5 mb-4 my-logo"
                         src="/resources/img/avatar.jpeg"
                         alt="my-logo" width={150} height={150}/>
                </div>
                <Row>
                    <Col className="col-md-6 text-center">Шипулин Олег Игоревич</Col>
                    <Col>Ису: 333955</Col>
                </Row>
                <Row>
                    <Col className="mt-5">Лабораторная работа номер 4. Для условия нажмите на Exercise на NavBar'e</Col>
                </Row>
            </Container>
        </>
    )
}

export default About