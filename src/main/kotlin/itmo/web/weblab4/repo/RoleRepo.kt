package itmo.web.weblab4.repo

import itmo.web.weblab4.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepo : JpaRepository<Role, Long> {
    fun findByRoleName(userName: String): Role?
}