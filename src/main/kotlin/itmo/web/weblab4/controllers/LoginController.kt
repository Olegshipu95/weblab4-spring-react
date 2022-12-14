package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.AuthRespDto
import itmo.web.weblab4.dto.EntranceDto
import itmo.web.weblab4.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/login")
class LoginController {
    @Autowired
    private lateinit var userRepository: UserRepository


    @PostMapping
    fun loginByUser(@RequestBody user: EntranceDto):ResponseEntity<Any>{
        val username = user.username
        val existsUser = userRepository.existsByUserName(username)
        return if(existsUser){
            ResponseEntity.badRequest().body(
                AuthRespDto("error","it's not your day, hacker")
            )
        }else{
            ResponseEntity.ok().body(
                AuthRespDto("nice")
            )
        }
    }
}