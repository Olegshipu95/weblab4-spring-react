import React, {useEffect, useState} from "react";
import {Table} from "react-bootstrap";

function tableFromArray(hits) {
    console.log("Аргументы -" + typeof hits)
    console.log(typeof hits)
    if (hits === undefined) return (<tr></tr>)
    return (
        Array.from(hits).map((hit, index) =>
            <tr key={index}>
                <td>{hit.cordX}</td>
                <td>{hit.cordY}</td>
                <td>{hit.cordR}</td>
                <td>{hit.exec}</td>
                <td>{hit.result}</td>
            </tr>
        )
    )
}

function MyTable(props) {
    return (
        <div className="glass">
            <Table striped bordered hover variant="dark" className="mt-5">
                <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>Z</th>
                    <th>Entry</th>
                    <th>Time</th>
                </tr>
                </thead>
                <tbody>
                {tableFromArray(props.hits)}
                </tbody>
            </Table>
        </div>
    )
}

export default MyTable