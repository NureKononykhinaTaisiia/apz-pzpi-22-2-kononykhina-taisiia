package com.example.rentjoy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentjoy.data.model.Product
import com.example.rentjoy.ui.navigation.Routes

@Composable
fun WishlistScreen(navController: NavController) {
    // Mock wishlist items
    val wishlistItems = remember {
        listOf(
            Product(
                pPrice = TODO(),
                pQuantity = TODO(),
                pSold = TODO(),
                pOffer = TODO(),
                pImages = TODO(),
                pStatus = TODO(),
                pCategory = TODO(),
                pRatingsReviews = TODO(),
                createdAt = TODO(),
                updatedAt = TODO(),
                __v = TODO(),
                _id = TODO(),
                pName = TODO(),
                pDescription = TODO()
            ),
            Product(
                pPrice = TODO(),
                pQuantity = TODO(),
                pSold = TODO(),
                pOffer = TODO(),
                pImages = TODO(),
                pStatus = TODO(),
                pCategory = TODO(),
                pRatingsReviews = TODO(),
                createdAt = TODO(),
                updatedAt = TODO(),
                __v = TODO(),
                _id = TODO(),
                pName = TODO(),
                pDescription = TODO()
            )
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wishlist") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(wishlistItems) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("product_detail/${product._id}")
                        },
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(product.pName, style = MaterialTheme.typography.h6)
                            Text("$${product.pPrice}", style = MaterialTheme.typography.body1)
                        }
                    }
                }
            }
        }
    }
} 