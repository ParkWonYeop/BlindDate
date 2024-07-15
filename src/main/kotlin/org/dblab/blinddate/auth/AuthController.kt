package org.dblab.blinddate.auth

import org.dblab.blinddate.auth.dto.CertificationDto
import org.dblab.blinddate.auth.dto.LoginDto
import org.dblab.blinddate.auth.dto.SignupDto
import org.dblab.blinddate.common.validation.ValidationSequence
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(
        @Validated(ValidationSequence::class)
        @RequestBody
        loginDto: LoginDto
    ) = authService.login(loginDto)

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @Validated(ValidationSequence::class)
        @RequestBody
        signupDto: SignupDto
    ) {
        authService.signup(signupDto)
        authService.sendCode(signupDto)
    }

    @PutMapping("/email")
    fun certificationEmail(
        @Validated(ValidationSequence::class)
        @RequestBody
        certificationDto: CertificationDto
    ) = authService.certificationEmail(certificationDto)

    @DeleteMapping
    fun deleteUser(@RequestParam email: String) = authService.deleteUser(email)
}
