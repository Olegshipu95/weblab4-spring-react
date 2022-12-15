import React, {useEffect} from "react";

function Canvas(props) {

    const canvasSize = 350
    const maxRadio = 2

    useEffect(() => {
        draw(props.currRadio)
        try {
            Array.from(props.points).forEach((point) => {
                if (point.coords[2] === props.currRadio) {
                    drawHit(point.coords[0], point.coords[1], 2, true)
                }
            })
        } catch (e) {}
    }, [props.currRadio, props.points])

    const handleClick = async (event) => {
        event.preventDefault()
        const hs = canvasSize/2
        const rect = event.target.getBoundingClientRect()
        const x = (event.clientX - rect.left - hs)/hs * maxRadio
        const y = -(event.clientY - rect.top - hs)/hs * maxRadio
        if (props.currRadio !== undefined) {
            props.submitter([x], y, props.currRadio)
        }
    }

    return (
        <div className="target glass canvas">
            <canvas id="graph" width="350" height="350" onClick={handleClick}/>
        </div>
    )
}

export default Canvas