package com.example.auth.model

data class UserCredentials(
    val username: String,
    val password: String
) {
    override fun toString(): String {
        return "UserCredentials(username='$username', password='******')"
    }
}
