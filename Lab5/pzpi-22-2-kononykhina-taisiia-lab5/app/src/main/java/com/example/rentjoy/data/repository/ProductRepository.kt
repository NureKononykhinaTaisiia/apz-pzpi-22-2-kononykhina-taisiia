package com.example.rentjoy.data.repository

import com.example.rentjoy.data.api.ApiClient
import com.example.rentjoy.data.model.Product

class ProductRepository {
    private val productApi = ApiClient.productApi

    suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts().Products
    }
} 