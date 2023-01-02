import React, {useEffect, useState} from "react";
import {Button, Col, Container, Form, Row, Table} from "react-bootstrap";
import {getHitsFromServer, validateR, validateX, validateY} from "./io";
import MyTable from "./MyTable";
import {useNavigate} from "react-router-dom";
const superagent = require('superagent');

export function Main() {
    const login = localStorage.getItem("lo gin")
    const tokenn = localStorage.getItem("token")
    const [myHits, setMyHits] = useState([])
    const [currX, setX] = useState(2)
    const [currY, setY] = useState(1)
    const [currR, setR] = useState(0.5)
    const navigate = useNavigate()
    useEffect(() => {
        if (localStorage.getItem("login") == null) {
            navigate("/")
        }
    })
    function sendPoint(){
        if(!validateX(currX)){
            alert("You should write the x coord")
            return
        }
        if(!validateY(currY)){
            alert("You should write the y coord")
            return
        }
        if(!validateR(currR)){
            alert("You should write the r coord")
            return
        }
        let data = {cordX: currX, cordY: currY, cordR: currR, username: login, token: null}
        superagent.post('http://localhost:8080/hits/shoot').send(data).set("Content-Type", "application/json")
            .then(resp => {
                resp.json
                setMyHits(resp.hits)
            })
            .catch(error => {
                alert(error)
            })
    }

    function getHits() {
        getHitsFromServer(login, tokenn, (result) => {
            setMyHits(result.hits)
        })
    }
    function sendDelete(){
        let data = {username: login}
        superagent.delete('http://localhost:8080/hits/shoot').set("Content-Type", "application/json").then(resp => {
                setMyHits([])
                alert(resp)
            })
            .catch(error => {
                alert(error)
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
                            <Form.Control type="y-cord" size="lg" placeholder="1"
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
                <Button variant="primary" className="me-2 mt-2" onClick={sendPoint}>Send data</Button>
                <Button variant="primary" className="mt-2" onClick={sendDelete}>Send Delete</Button>
            </Container>
            <MyTable hits={myHits}/>
            <div id="super-table"></div>
        </div>
    )
}