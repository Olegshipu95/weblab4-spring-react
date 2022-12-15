package itmo.web.weblab4.service

import itmo.web.weblab4.dto.HitDto
import org.springframework.stereotype.Service

@Service
class Checker {
    private fun checkCircle(x: Float, y: Float, r: Float): Boolean {
        return x * x + y * y <= r * r
    }

    private fun checkTriangle(x: Float, y: Float, r: Float): Boolean {
        return y >= -2 * x - r
    }

    private fun checkRectangle(x: Float, r: Float): Boolean {
        return x <= r / 2
    }

    fun checkHit(hit: HitDto): Boolean {
        val x = hit.cordX!!
        val y = hit.cordY!!
        val r = hit.cordR!!
        return if (x >= 0) {
            if (y <= 0) {
                checkTriangle(x, y, r)
            } else {
                checkRectangle(x, r)
            }
        } else {
            if (y <= 0) {
                checkCircle(x, y, r)
            } else {
                false
            }
        }

    }
}