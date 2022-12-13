import React from "react";
import {Container} from "react-bootstrap";

function Error() {
    return (
        <div>
            <Container id="main-container" className="d-grid h-100">
                <img className="Error-img mt-5" src="/resources/img/error-img.jpg" width={300} height={300}/>
                <h1 className="mt-5 text-center">/404/ <br/><br/> Страница не найдена <br/><br/> /404/</h1>
            </Container>
        </div>
    )
}

export default Error