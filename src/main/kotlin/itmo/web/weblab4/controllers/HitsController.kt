package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.FirstReqDto
import itmo.web.weblab4.dto.HitDto
import itmo.web.weblab4.dto.HitsForUserDto
import itmo.web.weblab4.dto.UsersHitsDto
import itmo.web.weblab4.repository.HitsRepository
//import itmo.web.weblab4.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/hits/list")
class HitsController {
    @Autowired
    private lateinit var hitsRepository: HitsRepository

//    @Autowired
//    private lateinit var tokenService: JwtService
    @PostMapping
    fun giveHitsToUser(@RequestBody info: FirstReqDto): ResponseEntity<Any> {
        val hits = hitsRepository.findAllByName(info.username)

        println("for user ${info.username} hits: $hits")
        return ResponseEntity.ok().body(
            HitsForUserDto("ok", null, hits!!.map {
                UsersHitsDto(
                    it.cordX!!, it.cordY!!, it.cordR!!,
                    it.execution!!,
                    it.result!!)
            })
        )
    }
    @DeleteMapping
    fun deleteHitsByUser(@Validated @RequestBody emptyHit: HitDto): ResponseEntity<Any> {
//        if (tokenService.validateToken(emptyHit.username, emptyHit.token)) {
            hitsRepository.deleteAllByName(emptyHit.username!!)
            val hits = hitsRepository.findAllByName(emptyHit.username!!)
            return ResponseEntity.ok().body(
                HitsForUserDto("ok", null, hits!!.map {
                    UsersHitsDto(
                        it.cordX!!, it.cordY!!, it.cordR!!,
                        it.execution!!,
                        it.result!!
                    )
                })
            )
//        } else {
//            ResponseEntity.badRequest().body(
//                HitsForUserDto("error", "expired token", null)
//            )
//        }
    }

}