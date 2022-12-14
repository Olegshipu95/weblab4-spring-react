package itmo.web.weblab4.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
open class UserEntity {
    @Id
    @SequenceGenerator(
        name = "shoots_sequence",
        sequenceName = "shoots_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null

    open var login: String? = null
    open var hashPass: String? = null
}