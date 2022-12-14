const superagent = require('superagent');


export function sendRegisterInfo(username, password, setErrorMessage, onSuccess) {
    let data = {username: username, password: password}
    superagent.put("http://localhost:8080/authentication").send(data).set("Content-Type", "application/json").then(res => {
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

export function validateLength(login, password, setMessage) {
    if (login.length <= 6 || password.length <= 6) {
        setMessage("Логин и пароль должны быть длиннее 4 символов")
        return false
    }
    return true
}

export function validateEmail(username) {
    return username.length >= 5;

}

export function validatePass(password) {
    return password.length >= 5
}

export function sendLoginInfo(username, password, setErrorMessage, onSuccess) {
    let data = {username: username, password: password}
    superagent.post("http://localhost:8080/login").send(data).set("Content-Type", "application/json").then(res => {
        console.log(res)
        onSuccess(res)
        return true
    })
        .catch((error) => {
            console.log(error)
            alert(error)
        })
    return false
}