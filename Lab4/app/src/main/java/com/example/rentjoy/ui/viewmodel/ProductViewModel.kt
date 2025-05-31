package com.example.rentjoy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentjoy.data.model.Product
import com.example.rentjoy.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                Log.d("ProductViewModel", "Fetching products from server...")
                
                val productList = repository.getAllProducts()
                Log.d("ProductViewModel", "Received ${productList.size} products from server")
                productList.forEach { product ->
                    Log.d("ProductViewModel", "Product: id=${product._id}, name=${product.pName}, price=${product.pPrice}")
                    Log.d("ProductViewModel", "Product images: ${product.pImages.joinToString()}")
                }
                
                _products.value = productList
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching products", e)
                _error.value = "Failed to load products: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retryLoading() {
        loadProducts()
    }
} 