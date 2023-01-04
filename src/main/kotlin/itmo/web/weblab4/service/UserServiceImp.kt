package itmo.web.weblab4.service

import itmo.web.weblab4.domain.Role
import itmo.web.weblab4.domain.User
import itmo.web.weblab4.repo.RoleRepo
import itmo.web.weblab4.repo.UserRepo
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Transactional
class UserServiceImp:UserService,UserDetailsService {
    @Autowired
    private lateinit var userRepo: UserRepo
    @Autowired
    private lateinit var roleRepo: RoleRepo


    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo.findByUserName(username)
        if(user == null){
            println("User not found in the database")
            throw UsernameNotFoundException("User not found in the database")
        }else{
            println("User fount in the db - $username")
        }
        val authorizaties= mutableListOf<SimpleGrantedAuthority>()
        user.roles.forEach{role->
            authorizaties.add(SimpleGrantedAuthority(role.roleName))
        }
        return org.springframework.security.core.userdetails.User(user.userName,user.password,authorizaties)
    }

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
        val user: User = userRepo.findByUserName(userName)
            ?: throw UsernameNotFoundException("User not found in the database")
        val role: Role = roleRepo.findByRoleName(roleName)
            ?: throw UsernameNotFoundException("Role not found in the database")
        user.roles.add(role)
    }

    override fun getUser(username: String):User?{
        println("get user $username")
        return userRepo.findByUserName(username)
    }

    override fun getUsers(): List<User> {
        println("get all users")
        return userRepo.findAll()
    }


}