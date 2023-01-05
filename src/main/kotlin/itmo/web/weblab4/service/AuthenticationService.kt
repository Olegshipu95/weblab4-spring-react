package itmo.web.weblab4.service

import itmo.web.weblab4.domain.Role
import itmo.web.weblab4.domain.User
import itmo.web.weblab4.dto.AuthenticationRequest
import itmo.web.weblab4.dto.AuthenticationResponse
import itmo.web.weblab4.dto.RegisterRequest
import itmo.web.weblab4.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService {
    @Autowired
    private lateinit var userRepo:UserRepo
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    private lateinit var jwtService: JwtService
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    fun register(request: RegisterRequest): ResponseEntity<Any> {

        val user = User()
        user.userName = request.username
        user.password = passwordEncoder.encode(request.password)
        val existsUser = userRepo.existsByUserName(request.username)
        if(existsUser){
            println("$request.username are using in system")
            return ResponseEntity.badRequest().body(
                "$request.username are using in system"
            )
        }
        user.role = Role.USER
        try {
            userRepo.save(user)
        } catch (e: Exception) {
            println("BD in not available")
            return ResponseEntity.badRequest().body(
                "BD in not available"
            )
        }
        val expirationTime:Long = jwtService.makeExpirationTime()
        val jwtToken = jwtService.generateToken(user, expirationTime)
        return ResponseEntity.ok(AuthenticationResponse(jwtToken, expirationTime))
    }
    fun authenticate(request: AuthenticationRequest): ResponseEntity<Any>{

        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username,request.password))
        println("start searching user - ${request.username} in bd")
        val user = userRepo.findByUserName(request.username).get()

        println("Start generating token for ${request.username}")
        val expirationTime:Long = jwtService.makeExpirationTime()
        val jwtToken = jwtService.generateToken(user, expirationTime)
        return ResponseEntity.ok(AuthenticationResponse(jwtToken, expirationTime))
    }
}