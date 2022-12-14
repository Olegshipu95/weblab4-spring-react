package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.AuthRespDto
import itmo.web.weblab4.dto.EntranceDto
import itmo.web.weblab4.entity.UserEntity
import itmo.web.weblab4.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authentication")
class AuthController {

    private lateinit var userRepository: UserRepository
    @PutMapping
    fun authByUser(@RequestBody user: EntranceDto): ResponseEntity<Any> {
        val username = user.username
        val existsUser = userRepository.existsByUserName(username)
        return if(existsUser){
            ResponseEntity.badRequest().body(
                AuthRespDto("error", "$username уже используется")
            )
        }else{
            val userEntity = UserEntity().also {
                it.userName = username
                it.password = user.password
            }
            userRepository.save(userEntity)
            ResponseEntity.ok().body(
                AuthRespDto("nice")
            )
        }
    }

}