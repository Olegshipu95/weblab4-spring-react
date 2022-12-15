import React, {useEffect, useState} from "react";
import {Button, Col, Container, Form, Row, Table} from "react-bootstrap";
import {getHitsFromServer} from "./io";
import MyTable from "./MyTable";
import {useNavigate} from "react-router-dom";
const superagent = require('superagent');

export function Main() {
    const login = localStorage.getItem("login")
    const token = localStorage.getItem("token")
    const [hitArray, setHits] = useState([])
    const [currX, setX] = useState()
    const [currY, setY] = useState()
    const [currR, setR] = useState()
    const navigate = useNavigate()
    useEffect(() => {
        if (localStorage.getItem("token") == null) {
            navigate("/")
        }
    })

    function sendPoint(){

        superagent().post('http://localhost:8080/hits/shoot',{ x: currX, y: currY, r: currR})
            .then(resp => {
                setHits(resp.hits)
            })
            .catch(error => {
                alert(error)
            })
    }

    function getHits() {
        getHitsFromServer(login, token, (result) => {
            setHits(result.hits)
        })
    }

    useEffect(() => {
        getHits()
    }, [])


    return (
        <div className="main-container">
            <h1 className="text-center">Форма для отправки координат</h1>
            <Form>
                <Row className="align-self-end">
                    <Col className="col-md-1 offset-md-1">
                        <Form.Group>
                            <Form.Label>X координата</Form.Label>
                            <Form.Control as="select" onChange={e => setX(e.target.value)}>
                                <option value="-2">-2</option>
                                <option value="-1.5">-1.5</option>
                                <option value="-1">-1</option>
                                <option value="-0.5">-0.5</option>
                                <option value="0">0</option>
                                <option value="0.5">0.5</option>
                                <option value="1">1</option>
                                <option value="1.5">1.5</option>
                                <option value="2.5">2.5</option>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    <Col className="col-md-2  offset-md-1 mt-4">
                        <Form.Group className="input-group-sm">
                            <Form.Label>Y координата</Form.Label>
                            <Form.Control type="y-cord" size="lg" placeholder="Введите y"
                                          className="position-relative" onChange={event => setY(event.target.value)}/>
                        </Form.Group>
                    </Col>
                    <Col className="col-md-1 offset-md-1 mt-4">
                        <Form.Group>
                            <Form.Label>R</Form.Label>
                            <Form.Control as="select" onChange={e => setR(e.target.value)}>
                                <option value="0.5">0.5</option>
                                <option value="1">1</option>
                                <option value="1.5">1.5</option>
                                <option value="2">2</option>
                                <option value="2.5">2.5</option>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    <Col className="offset-md-1">
                        <img src="/resources/img/my-var.png" height="250px" width="250px"/>
                    </Col>
                </Row>
            </Form>
            <Container className="text-center w-100">
                {/*<Button variant="primary" className="me-2 mt-2" onClick={sign_in}></Button>*/}
                {/*<Button variant="primary" className="mt-2" onClick={}></Button>*/}
            </Container>
            <MyTable hits={hitArray}/>
        </div>
    )
}