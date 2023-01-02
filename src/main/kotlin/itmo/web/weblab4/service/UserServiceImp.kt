package itmo.web.weblab4.service

import itmo.web.weblab4.domain.Role
import itmo.web.weblab4.domain.User
import itmo.web.weblab4.repo.RoleRepo
import itmo.web.weblab4.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImp:UserService {
    @Autowired
    private lateinit var userRepo: UserRepo
    @Autowired
    private lateinit var roleRepo: RoleRepo
    override fun saveUser(user: User): User {
        TODO("Not yet implemented")
    }

    override fun saveRole(role: Role): Role {
        TODO("Not yet implemented")
    }

    override fun addRoleToUser(userName: String, roleName: String) {
        TODO("Not yet implemented")
    }

    override fun getUser(username: String) {
        TODO("Not yet implemented")
    }

    override fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }
}