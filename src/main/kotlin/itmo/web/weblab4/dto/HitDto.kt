package itmo.web.weblab4.dto

import org.springframework.lang.Nullable

data class HitDto(
    var username: String,
    var cordX: Float?,
    var cordY: Float?,
    var cordR: Float?
)