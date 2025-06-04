package com.example.rentjoy.data.api

import com.example.rentjoy.data.model.AuthResponse
import com.example.rentjoy.data.model.SignInRequest
import com.example.rentjoy.data.model.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/signin")
    suspend fun signIn(@Body request: SignInRequest): Response<AuthResponse>

    @POST("/api/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<AuthResponse>
} 