const superagent = require('superagent');


export function getHitsFromServer(login, onSuccess, OnError){
    let myHeaders = new Headers();
    myHeaders.append('Content-Type','application/json')
    console.log("начинается запрос")
    let accessToken = localStorage.getItem("accessToken"); // объявляем локальную переменную tokenData
    let refreshToken = localStorage.getItem("refreshToken")
    let expirationTime = localStorage.getItem("expirationTime")
    console.log("access token - "+ accessToken)
    console.log("refreshToken - "+refreshToken)
    // if (!accessToken || !refreshToken) {
    //     localStorage.removeItem("refreshToken")
    //     localStorage.removeItem("accessToken")
    //     localStorage.removeItem("login")
    //     OnError()
    // }

    if (Date.now() >=  expirationTime * 1000){
        myHeaders.append('RefreshToken', `Bearer ${refreshToken}`)
    }
    else{
        myHeaders.append('AccessToken', `Bearer ${accessToken}`)
    }
        fetch("http://localhost:8080/api/hits/list",{
        "method": "GET",
        "headers": myHeaders,
    }).then(response=>response.json()).then(data=>{
            console.log(data.hits)
            onSuccess(data.hits)
            return true
        }).catch(error => {
            alert(error)
        })
    return false
}

export function validateX(x){
    return !(x === undefined || x == null);
}
export function validateY(y){
    return !(y === undefined || y==null);
}
export function validateR(r){
    return !(r === undefined||r==null)
}

