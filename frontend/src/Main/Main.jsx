import React, {useEffect, useState} from "react";
import {Col, Form, Row, Table} from "react-bootstrap";
import {getHitsFromServer} from "./io";
import MyTable from "./MyTable";
import Canvas from "../Canvas/Canvas";

// import {Canvas} from "../Canvas/Canvas";
export function Main() {
    const login = localStorage.getItem("login")
    const token = localStorage.getItem("token")
    const [hitArray, setHits] = useState([])
    const [currX, setX] = useState()
    const [currY, setY] = useState()
    const [currR, setR] = useState()
    // useEffect(() => {
    //     if (localStorage.getItem("accessToken") !== null || localStorage.getItem("refreshToken") !== null) {
    //         props.getData()
    //     }else {
    //         navigate("/login")
    //     }
    // }, [])


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
                        <Canvas
                            points={hitArray}
                            currRadio={currR}
                            // submitter={sendCertainRequest}
                        />
                    </Col>
                </Row>
            </Form>
            <MyTable hits={hitArray} />
        </div>
    )
}