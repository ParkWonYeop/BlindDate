package org.dblab.blinddate.auth

import org.dblab.blinddate.auth.dto.LoginDto
import org.dblab.blinddate.auth.dto.SignupDto
import org.springframework.http.HttpStatus
import org.dblab.blinddate.common.validation.ValidationSequence
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
){
    @PostMapping("/login")
    fun login(
        @Validated(ValidationSequence::class)
        @RequestBody
        loginDto: LoginDto,
    ) = authService.login(loginDto)

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @Validated(ValidationSequence::class)
        @RequestBody
        signupDto: SignupDto,
    ) = authService.signup(signupDto)
}