package itmo.web.weblab4.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
class SecurityConfig {

    private val PUBLIC_URLS = arrayOf("/public/**")
    private val NON_AUTH_ONLY_URLS = arrayOf("/api/auth/**")
    private val AUTH_ONLY_URLS = arrayOf("/api/**")
    private val ADMIN_URLS = arrayOf("/api/admin/**")
    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var jwtAuthFilter: JwtAuthFilter
    @Autowired
    private lateinit var authenticationProvider: AuthenticationProvider
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http.cors().and().csrf().disable().authorizeHttpRequests()
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/**").authenticated()
            .requestMatchers("/api/hits/**").authenticated()
            .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
            .anyRequest().permitAll()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}