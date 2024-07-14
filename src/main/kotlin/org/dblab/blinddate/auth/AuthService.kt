package org.dblab.blinddate.auth

import jakarta.transaction.Transactional
import org.dblab.blinddate.auth.dto.LoginDto
import org.dblab.blinddate.auth.dto.LoginResponseDto
import org.dblab.blinddate.auth.dto.SignupDto
import org.dblab.blinddate.common.entity.entities.TokenEntity
import org.dblab.blinddate.common.entity.entities.UserEntity
import org.dblab.blinddate.common.errorHandle.CustomException
import org.dblab.blinddate.common.errorHandle.constant.CommunalResponse
import org.dblab.blinddate.common.repository.TokenRepository
import org.dblab.blinddate.common.repository.UserRepository
import org.dblab.blinddate.common.utils.JwtUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val encoder: BCryptPasswordEncoder,
    private val tokenRepository: TokenRepository,
    @Value("\${secret_key}")
    private val secretKey: String,
) {
    private val jwtUtil = JwtUtil()
    private val log = LoggerFactory.getLogger(AuthService::class.java)

    @Transactional
    fun login(
        loginDto: LoginDto,
    ): LoginResponseDto {
        val userEntity = userRepository.findByEmail(loginDto.email)
            ?: throw AccessDeniedException("전화번호가 일치하지 않습니다.")

        if (!encoder.matches(loginDto.password, userEntity.password)) {
            throw AccessDeniedException("비밀번호가 일치하지 않습니다.")
        }

        val accessToken = jwtUtil.generateToken(userEntity, secretKey)
        val refreshToken = jwtUtil.createRefreshToken(secretKey)

        val tokenEntity = tokenRepository.findByUser(userEntity)?.apply {
            this.accessToken = accessToken
            this.refreshToken = refreshToken
        }
            ?: TokenEntity(userEntity, accessToken, refreshToken)

        tokenRepository.save(tokenEntity)

        log.info("login : success - " + userEntity.email)

        return LoginResponseDto(refreshToken, accessToken)
    }

    @Transactional
    fun signup(
        signupDto: SignupDto,
    ) {
        val email = signupDto.email

        userRepository.findByEmail(email)?.let {
            throw CustomException(CommunalResponse.ALREADY_SIGNUP_PHONENUMBER)
        }

        val passwordEncode = encoder.encode(signupDto.password)

        val userEntity = UserEntity(email, passwordEncode, signupDto.name, signupDto.nickName, signupDto.gender)

        userRepository.save(userEntity)
    }
}