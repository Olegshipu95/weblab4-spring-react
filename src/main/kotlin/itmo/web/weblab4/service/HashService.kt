package itmo.web.weblab4.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class HashService {

    val encoder = BCryptPasswordEncoder()

    fun encodePassword(pass: String): String {
        return encoder.encode(pass)
    }

    fun matchPassword(candidate: String, hash: String): Boolean {
        return encoder.matches(candidate, hash)
    }
}