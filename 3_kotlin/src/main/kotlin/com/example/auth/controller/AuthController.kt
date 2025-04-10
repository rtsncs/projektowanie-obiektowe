package com.example.auth.controller

import com.example.auth.model.UserCredentials
import com.example.auth.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/login")
    fun login(@RequestBody userCredentials: UserCredentials): String {
        return if (authService.authenticate(userCredentials.username, userCredentials.password)) {
            "Login successful"
        } else {
            "Invalid credentials"
        }
    }

    @GetMapping("/users")
    fun getAllUsers(): List<String> {
        return authService.getAllUsers()
    }
}
