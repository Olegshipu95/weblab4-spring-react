package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.*
import itmo.web.weblab4.entity.HitEntity
import itmo.web.weblab4.repository.HitsRepository
import itmo.web.weblab4.service.Checker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime


@RestController
@CrossOrigin
@RequestMapping("/hits/shoot")
class ShootController {
    @Autowired
    private lateinit var hitsRepository: HitsRepository

    @Autowired
    private lateinit var checker: Checker

    @PostMapping
    fun hitsFromUser(@RequestBody hitRec: HitDto): ResponseEntity<Any> {
        val start = System.nanoTime()
        val res = checker.checkHit(hitRec).toString()
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
        return ResponseEntity.ok().body(
            HitsForUserDto("ok", null, hits!!.map {
                UsersHitsDto(
                    it.cordX!!, it.cordY!!, it.cordR!!,
                    it.execution!!,
                    it.result!!
                )
            })
        )

    }
}