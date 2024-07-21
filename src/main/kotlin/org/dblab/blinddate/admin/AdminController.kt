package org.dblab.blinddate.admin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminService: AdminService,
) {
    @GetMapping("/profile")
    fun getPermitList() = adminService.getPermitList()
}