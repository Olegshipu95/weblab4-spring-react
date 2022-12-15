package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.FirstReqDto
import itmo.web.weblab4.dto.HitsForUserDto
import itmo.web.weblab4.dto.UsersHitsDto
import itmo.web.weblab4.repository.HitsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/hits/list")
class HitsController {
    @Autowired
    private lateinit var hitsRepository: HitsRepository

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

}