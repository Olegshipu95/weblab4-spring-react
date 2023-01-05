package itmo.web.weblab4.dto

data class AuthenticationResponse(var token:String, var expirationTime:Long)