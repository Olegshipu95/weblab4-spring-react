package itmo.web.weblab4.repo

import itmo.web.weblab4.domain.User
import itmo.web.weblab4.entity.HitEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo: JpaRepository<User, Long> {
    fun findByUserName(userName: String): User?
}