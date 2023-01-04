package itmo.web.weblab4.service

import itmo.web.weblab4.domain.Role
import itmo.web.weblab4.domain.User

interface UserService {
    fun saveUser(user: User):User;
    fun saveRole(role: Role):Role;
    fun addRoleToUser(userName:String, roleName:String)
    fun getUser(username:String): User?;
    fun getUsers():List<User>

}