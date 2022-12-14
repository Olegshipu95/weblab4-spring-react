package itmo.web.weblab4.repository

import itmo.web.weblab4.entity.HitEntity
import org.springframework.data.jpa.repository.JpaRepository

interface HitsRepository:JpaRepository<HitEntity,Long> {
}