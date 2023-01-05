package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.*
import itmo.web.weblab4.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
class AuthController {

    @Autowired
    private lateinit var service: AuthenticationService

    @PostMapping("/authentication")
    fun authByUser(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<Any> {
        println("Handle authentication request")
        return service.authenticate(authenticationRequest)
    }

    @PostMapping("/register")
    fun register(@RequestBody requestBody: RegisterRequest):ResponseEntity<Any>{
        println("Handle register request")
        return service.register(requestBody)
    }
}