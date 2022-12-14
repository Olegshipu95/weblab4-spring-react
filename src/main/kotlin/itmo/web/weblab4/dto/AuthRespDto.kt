package itmo.web.weblab4.dto

data class AuthRespDto(
    var result: String,
    var errorMessage: String? = null,
    var token: String? = null,
)

