package com.example.rentjoy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentjoy.data.model.Product
import com.example.rentjoy.ui.navigation.Routes
import com.example.rentjoy.ui.theme.RentJoyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    // Mock data for cart items
    val cartItems = listOf(
        Product(
            "1", "Camera", "100.0",
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
            __v = TODO()
        ),
        Product(
            "2", "Laptop", "500.0",
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
            __v = TODO()
        )
    )

    RentJoyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Cart") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(cartItems) { item ->
                        CartItem(item)
                    }
                }

                Button(
                    onClick = { navController.navigate(Routes.ORDER) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Proceed to Checkout")
                }
            }
        }
    }
}

@Composable
fun CartItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = product.pName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "$${product.pPrice}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            IconButton(onClick = { /* TODO: Remove from cart */ }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Remove from cart")
            }
        }
    }
} 