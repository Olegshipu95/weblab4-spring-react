package itmo.web.weblab4.security

import itmo.web.weblab4.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var jwtService: JwtService

    @Autowired
    private lateinit var userDetailsService: UserDetailsService
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("start filter")
        val authHeader: String? = request.getHeader("accesstoken")
        val jwt: String
        val userName: String
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        println(authHeader)
        jwt = authHeader.substring(7)
        println(jwt)
        userName = jwtService.extractUsername(jwt)
        println("username - $userName")
        println("start checking the jwt")
        if (!userName.isNullOrBlank() && jwtService.validateToken(jwt)) {
            println("validate is ok")
            val userDetails: UserDetails = this.userDetailsService.loadUserByUsername(userName)
            if (jwtService.isTokenValid(jwt, userDetails)) {
                val authToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, userDetails.authorities
                )
                authToken.setDetails(WebAuthenticationDetailsSource().buildDetails(request))
                SecurityContextHolder.getContext().setAuthentication(authToken)
            }
        }
        filterChain.doFilter(request, response)
    }
}