package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.FirstReqDto
import itmo.web.weblab4.dto.HitDto
import itmo.web.weblab4.dto.HitsForUserDto
import itmo.web.weblab4.dto.UsersHitsDto
import itmo.web.weblab4.entity.HitEntity
import itmo.web.weblab4.repository.HitsRepository
import itmo.web.weblab4.service.Checker
//import itmo.web.weblab4.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@CrossOrigin
@RequestMapping("/api/hits")
class HitsController {
    @Autowired
    private lateinit var hitsRepository: HitsRepository
    @Autowired
    private lateinit var checker: Checker

//    @Autowired
//    private lateinit var tokenService: JwtService
    @GetMapping("/list")
    fun giveHitsToUser(principal: Principal): ResponseEntity<Any> {
        val hits = hitsRepository.findAllByName(principal.name)

        println("for user ${principal.name} hits: $hits")
        return ResponseEntity.ok().body(
            HitsForUserDto("ok", null, hits!!.map {
                UsersHitsDto(
                    it.cordX!!, it.cordY!!, it.cordR!!,
                    it.execution!!,
                    it.result!!)
            })
        )
    }
    @DeleteMapping("/list")
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

    @PostMapping("/shoot")
    fun hitsFromUser(@RequestBody hitRec: HitDto): ResponseEntity<Any> {
        println("Start controller shoot")
        val start = System.nanoTime()
//        return if (tokenService.validateToken(hitRec.username, hitRec.token)) {
        println("hi")
        val res = checker.checkHit(hitRec).toString()
        println("checker - $checker")
        val checkedHitEntity = HitEntity().also {
            it.cordX = hitRec.cordX
            it.cordY = hitRec.cordY
            it.cordR = hitRec.cordR
            it.name = hitRec.username
            it.result = res
            it.execution = (System.nanoTime() - start) / 1000
        }
        hitsRepository.save(checkedHitEntity)
        val hits = hitsRepository.findAllByName(hitRec.username)
        println("my hits-$hits")
        return ResponseEntity.ok().body(
            HitsForUserDto("ok", null, hits!!.map {
                UsersHitsDto(
                    it.cordX!!, it.cordY!!, it.cordR!!,
                    it.execution!!,
                    it.result!!
                )
            })
        )

//        }else{
//            ResponseEntity.badRequest().body(
//                HitsForUserDto("error", "expired token", null)
//            )
//        }
    }
}