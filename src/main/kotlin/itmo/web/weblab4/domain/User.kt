package itmo.web.weblab4.domain
import jakarta.persistence.*
import java.util.Collections

@Entity
@Table(name = "users")
open class User {
    @Id
    @SequenceGenerator(
        name = "shoots_sequence",
        sequenceName = "shoots_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null

    open var userName: String? = null
    open var password: String? = null
    @ManyToMany(fetch = FetchType.EAGER)
    open var roles = mutableListOf<Role>()
}