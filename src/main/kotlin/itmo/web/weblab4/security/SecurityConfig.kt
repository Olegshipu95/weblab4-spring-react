package itmo.web.weblab4.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http.cors().and().csrf().disable()
        return http.build()
    }
    @Bean
    fun authManager(auth: AuthenticationManagerBuilder){
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }
    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder();
    }
}