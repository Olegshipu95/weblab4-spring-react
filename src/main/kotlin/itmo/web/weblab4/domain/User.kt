package itmo.web.weblab4.domain
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
open class User : UserDetails{
    @Id
    @SequenceGenerator(
        name = "shoots_sequence",
        sequenceName = "shoots_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    open var userName: String? = null
    @get:JvmName("getPass")
    var password: String? = null
    @Enumerated(EnumType.STRING)
    var role:Role? = null
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role !!.name))
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}