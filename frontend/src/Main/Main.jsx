import React, {useEffect} from "react";
import {Col, Form, Row, Table} from "react-bootstrap";
// import {Canvas} from "../Canvas/Canvas";
export function Main() {
    return (
        <div className="main-container">
            <h1 className="text-center">Форма для отправки координат</h1>
            <Form>
                <Row className="align-self-end">
                    <Col className="col-md-1 offset-md-1">
                        <Form.Group>
                            <Form.Label>X координата</Form.Label>
                            <Form.Control as="select">
                                <option>-2</option>
                                <option>-1.5</option>
                                <option>-1</option>
                                <option>-0.5</option>
                                <option>0</option>
                                <option>0.5</option>
                                <option>1</option>
                                <option>1.5</option>
                                <option>2.5</option>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    <Col className="col-md-2  offset-md-1 mt-4">
                        <Form.Group className="input-group-sm">
                            <Form.Label>Y координата</Form.Label>
                            <Form.Control type="y-cord" size="lg" placeholder="Введите y" className="position-relative"/>
                        </Form.Group>
                    </Col >
                    <Col className="col-md-1 offset-md-1 mt-4">
                        <Form.Group>
                            <Form.Label>R</Form.Label>
                            <Form.Control as="select">
                                <option>0.5</option>
                                <option>1</option>
                                <option>1.5</option>
                                <option>2</option>
                                <option>2.5</option>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    {/*<Col className="offset-md-1">*/}
                    {/*    <Canvas/>*/}
                    {/*</Col>*/}
                </Row>
            </Form>
            <Table striped bordered hover variant="dark" className="mt-5">
                <thead>
                <tr>
                    <th>#</th>
                    <th>X</th>
                    <th>Y</th>
                    <th>Z</th>
                    <th>Entry</th>
                    <th>Time</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>Yes</td>
                    <td>10</td>
                </tr>
                </tbody>
            </Table>
        </div>
    )
}