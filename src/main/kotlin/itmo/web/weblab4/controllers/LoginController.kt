package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.AuthRespDto
import itmo.web.weblab4.dto.EntranceDto
import itmo.web.weblab4.repository.UserRepository
import itmo.web.weblab4.service.HashService
//import itmo.web.weblab4.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/login")
class LoginController {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var hashService:HashService
//    @Autowired
//    private lateinit var tokenService: JwtService


    @PostMapping
    fun loginByUser(@RequestBody user: EntranceDto):ResponseEntity<Any>{
        val username = user.username
        val userFromDb = userRepository.findByUserName(username)
        return if(userFromDb==null|| !hashService.matchPassword(user.password, userFromDb.password!!)){
            ResponseEntity.badRequest().body(
                AuthRespDto("error","it's not your day, hacker")
            )
        }else{
//            val usersToken = tokenService.generateForUser(username)
            ResponseEntity.ok().body(
                AuthRespDto("nice")
            )
        }
    }
}