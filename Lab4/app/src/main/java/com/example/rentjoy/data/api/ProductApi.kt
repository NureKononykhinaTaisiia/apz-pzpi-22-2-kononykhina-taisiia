package com.example.rentjoy.data.api

import com.example.rentjoy.data.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("/api/product/all-product")
    suspend fun getAllProducts(): ProductResponse
} 