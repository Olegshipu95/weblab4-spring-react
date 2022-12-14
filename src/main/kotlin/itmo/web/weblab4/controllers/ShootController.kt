package itmo.web.weblab4.controllers

import itmo.web.weblab4.dto.HitDto
import itmo.web.weblab4.repository.HitsRepository
import itmo.web.weblab4.service.Checker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/shoot")
class ShootController {
    @Autowired
    private lateinit var hitsRepository: HitsRepository
    @Autowired
    private lateinit var checker: Checker

    @PostMapping
    fun hitsFromUser(@RequestBody emptyHit: HitDto){

    }
}