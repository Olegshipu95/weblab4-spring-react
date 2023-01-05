package itmo.web.weblab4.entity

import jakarta.persistence.*


@Entity
@Table(name = "roles")
open class Role {
    @Id
    @SequenceGenerator(
        name = "shoots_sequence",
        sequenceName = "shoots_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null
    open var roleName: String? = null
}