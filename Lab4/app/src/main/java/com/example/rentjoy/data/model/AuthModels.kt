package com.example.rentjoy.data.model

data class SignInRequest(
    val email: String,
    val password: String
)

data class SignUpRequest(
    val cPassword: String,
    val email: String,
    val name: String,
    val password: String,
    val userRole: String = "user" // Default role
)

data class AuthResponse(
    val token: String,
    val user: User
)

data class User(
    val _id: String,
    val name: String,
    val email: String,
    val userRole: String
) 