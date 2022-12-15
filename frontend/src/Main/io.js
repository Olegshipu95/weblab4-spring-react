const superagent = require('superagent');


export function getHitsFromServer(login,token, onSuccess){
    let data = {
        username: login, token: token
    }
    console.log("начинается запрос")
    superagent.post("http://localhost:8080/hits/list").send(data).set("Content-Type", "application/json").then(res => {
        console.log(res)
        onSuccess(res)
        return true
    })
        .catch((error) => {
            console.log(error)
            alert("Проблемка с серваком")
        })
    return false
}
