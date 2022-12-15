package itmo.web.weblab4.dto

data class HitsForUserDto (
    var result: String,
    var errorMessage: String? = null,
    var hits: List<UsersHitsDto>?
)