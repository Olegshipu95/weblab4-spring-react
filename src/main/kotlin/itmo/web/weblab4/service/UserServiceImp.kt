package itmo.web.weblab4.service

import itmo.web.weblab4.domain.Role
import itmo.web.weblab4.domain.User
import itmo.web.weblab4.repo.RoleRepo
import itmo.web.weblab4.repo.UserRepo
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class UserServiceImp:UserService {
    @Autowired
    private lateinit var userRepo: UserRepo
    @Autowired
    private lateinit var roleRepo: RoleRepo


    override fun saveUser(user: User): User {
        println("Save user - $user to db")
        return userRepo.save(user)
    }

    override fun saveRole(role: Role): Role {
        println("Save role - $role to db")
        return roleRepo.save(role)
    }

    override fun addRoleToUser(userName: String, roleName: String) {
        println("add role - $roleName to userName - $userName")
        val user:User = userRepo.findByUserName(userName)
        val role:Role = roleRepo.findByRoleName(roleName)
        user.roles.add(role)
    }

    override fun getUser(username: String):User {
        println("get user $username")
        return userRepo.findByUserName(username)
    }

    override fun getUsers(): List<User> {
        println("get all users")
        return userRepo.findAll()
    }
}