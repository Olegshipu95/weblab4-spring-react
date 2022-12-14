package itmo.web.weblab4.dto

data class AuthRespDto(var result: String,
                       var token: String? = null,
                       var errorMessage: String? = null)

