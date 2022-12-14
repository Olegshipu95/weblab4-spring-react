package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.AuthRespDto
import itmo.web.weblab4.dto.EntranceDto
import itmo.web.weblab4.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/login")
class LoginController {
    @Autowired
    private lateinit var userRepository: UserRepository


    @PostMapping
    fun loginByUser(@RequestBody user: EntranceDto):ResponseEntity<Any>{
        val username = user.username
        val existsUser = userRepository.findByUserName(username)
        return if(existsUser==null){
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