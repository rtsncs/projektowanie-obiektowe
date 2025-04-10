package com.example.auth.service

import org.springframework.stereotype.Service

@Service
class AuthService {
    private val users = mapOf(
        "user" to "password",
        "admin" to "admin123"
    )

    fun authenticate(username: String, password: String): Boolean {
        return users[username] == password
    }

    fun getAllUsers(): List<String> {
        return users.keys.toList()
    }
}
