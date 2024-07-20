package org.dblab.blinddate.user

import org.dblab.blinddate.common.validation.ValidationSequence
import org.dblab.blinddate.user.dto.ProfileDto
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/profile")
    fun registerProfile(
        @Validated(ValidationSequence::class)
        @RequestBody
        profileDto: ProfileDto
    ) = userService.registerProfile(profileDto)

    @GetMapping("/profile")
    fun inquiryProfile(
        @RequestParam(value = "email")
        email: String,
    ) = userService.inquiryProfile(email)

    @PutMapping("/profile/open")
    fun changeOpenProfile() = userService.changeOpenProfile()
}