package itmo.web.weblab4.repository

import itmo.web.weblab4.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository:JpaRepository<UserEntity,Long> {
    fun existsByUserName(username: String?): Boolean

    fun findByUserName(username: String?): Optional<UserEntity>?

}