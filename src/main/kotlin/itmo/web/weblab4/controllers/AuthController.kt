package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.AuthRespDto
import itmo.web.weblab4.dto.EntranceDto
import itmo.web.weblab4.entity.UserEntity
import itmo.web.weblab4.repository.UserRepository
import itmo.web.weblab4.service.HashService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/authentication")
class AuthController {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var hashService: HashService

    @PutMapping
    fun authByUser(@RequestBody user: EntranceDto): ResponseEntity<Any> {
        val username = user.username
        println("$username start registration")
        val existsUser = userRepository.existsByUserName(username)

        return if(existsUser){
            println("$username are using in system")
            ResponseEntity.badRequest().body(
                AuthRespDto("error", "$username уже используется")
            )
        }
        else{
            println("$username start saving his login to db")
            val userEntity = UserEntity().also {
                it.userName = username
                it.password = hashService.encodePassword(user.password)
            }
            userRepository.save(userEntity)
            println("$username now in DB. Nice")
            ResponseEntity.ok().body(
                AuthRespDto("nice")
            )
        }
    }

}