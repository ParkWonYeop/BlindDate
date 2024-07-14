package org.dblab.blinddate.common.config

import org.dblab.blinddate.common.enum.PermissionEnum
import org.springframework.beans.factory.annotation.Value
import org.dblab.blinddate.common.filter.JwtFilter
import org.dblab.blinddate.common.repository.TokenRepository
import org.dblab.blinddate.common.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    @Value("\${secret_key}")
    private val secretKey: String,
) {
    @Bean
    protected fun securityFilterChain(
        http: HttpSecurity,
    ): DefaultSecurityFilterChain? = http
        .httpBasic { it.disable() }
        .csrf { it.disable() }
        .authorizeHttpRequests {
            it.requestMatchers("/auth/login", "/auth/signup", "/auth/test").permitAll()
            it.requestMatchers(HttpMethod.PUT, "/auth/token").permitAll()
        }
        .sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        .addFilterBefore(JwtFilter(userRepository, tokenRepository, secretKey), UsernamePasswordAuthenticationFilter::class.java)
        .build()

    @Bean
    protected fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}