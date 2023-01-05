import {json} from "react-router-dom";
import axios from "axios";
const superagent = require('superagent');


export function sendRegisterInfo(username, password, setErrorMessage, onSuccess) {
    let data = {username: username, password: password}
    // superagent.post("http://localhost:8080/api/auth/register").send(data).set("Content-Type", "application/json").then(res => {
    //     console.log(res)
    //     localStorage.setItem("token", res.token)
    //     console.log(localStorage.getItem("token"))
    //     console.log("время сейчас" + Date.now())
    //     console.log("время работы" + res.expirationTime * 1000)
    //     localStorage.setItem("login", username)
    //     onSuccess(res)
    //     return true
    // })
    //     .catch((error) => {
    //         alert(error)
    //     })

    fetch("http://localhost:8080/api/auth/register",{
        "method": "POST",
        "headers": {
            "content-type": "application/json",
        },
        "body": JSON.stringify(data)
    }).then(resp=>{
        if(resp.status>=200 && resp.status<300){
            resp.json().then(res=>{
                console.log(res)
                localStorage.setItem("token", res.token)
                localStorage.setItem("expirationTime",res.expirationTime)
                console.log(localStorage.getItem("token"))
                console.log("время сейчас" + Date.now())
                console.log("время работы" + res.expirationTime * 1000)
                localStorage.setItem("login", username)})
                onSuccess(resp)
            return true
        }
        else if(resp.status === 500){
            alert("сервер недоступен")
        }
        else alert(resp)
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
    fetch("http://localhost:8080/api/auth/authentication",{
        "method": "POST",
        "headers": {
            "content-type": "application/json",
        },
        "body": JSON.stringify(data)
    }).then(resp=>{
        if(resp.status>=200 && resp.status<300){
            resp.json().then(res=>{
                console.log(res)
                localStorage.setItem("token", res.token)
                localStorage.setItem("expirationTime",res.expirationTime)
                console.log(localStorage.getItem("token"))
                console.log("время сейчас" + Date.now())
                console.log("время работы" + res.expirationTime * 1000)
                localStorage.setItem("login", username)})
            onSuccess(resp)
            return true
        }
        else if(resp.status === 500){
            alert("сервер недоступен")
        }
        else alert(resp)
    })
    return false
}