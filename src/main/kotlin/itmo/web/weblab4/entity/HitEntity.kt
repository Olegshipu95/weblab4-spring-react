package itmo.web.weblab4.entity

import jakarta.persistence.*
import java.time.ZonedDateTime


@Entity
@Table(name = "hits")
open class HitEntity {
    @Id
    @SequenceGenerator(
        name = "shoots_sequence",
        sequenceName = "shoots_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null

    open var name: String? = null
    open var cordX: Float? = null
    open var cordY: Float? = null
    open var cordR: Float? = null
    open var execution: Long? = null
    open var result: String? = null
}