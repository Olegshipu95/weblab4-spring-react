package itmo.web.weblab4.repo

import itmo.web.weblab4.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepo: JpaRepository<User, Long> {
    fun findByUserName(userName: String): Optional<User>

    fun existsByUserName(userName: String):Boolean
}