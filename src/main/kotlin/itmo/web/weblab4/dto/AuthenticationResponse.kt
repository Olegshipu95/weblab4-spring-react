package itmo.web.weblab4.dto

data class AuthenticationResponse(var jwtAccessToken:String, var expirationTime:Long, var jwtRefreshToken:String)