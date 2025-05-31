package com.example.rentjoy.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.rentjoy.R
import com.example.rentjoy.data.model.Product
import com.example.rentjoy.ui.theme.RentJoyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    product: Product
) {
    RentJoyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(product.pName) },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: Add to cart */ }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Add to cart")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Product Images
                if (product.pImages.isNotEmpty()) {
                    val pagerState = rememberPagerState()

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(Color.LightGray)
                    ) {
                        HorizontalPager(
                            count = product.pImages.size,
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            val imageUrl = "http://192.168.1.54:8000/uploads/products/${product.pImages[page]}"

                            val imageRequest = ImageRequest.Builder(LocalContext.current)
                                .data(imageUrl)
                                .crossfade(true)
                                .size(Size.ORIGINAL)
                                .build()

                            val painter = rememberAsyncImagePainter(
                                model = imageRequest,
                                contentScale = ContentScale.Fit
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.surface),
                                contentAlignment = Alignment.Center
                            ) {
                                when (val state = painter.state) {
                                    is AsyncImagePainter.State.Loading -> {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(48.dp),
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    is AsyncImagePainter.State.Error -> {
                                        val error = state.result.throwable
                                        Log.e("ProductDetailScreen", "Error state: $error")
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Warning,
                                                contentDescription = "Error loading image",
                                                modifier = Modifier.size(48.dp),
                                                tint = MaterialTheme.colorScheme.error
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Failed to load image: ${error.message}",
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }
                                    }
                                    is AsyncImagePainter.State.Success -> {
                                        Image(
                                            painter = painter,
                                            contentDescription = "Product image ${page + 1}",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Fit
                                        )
                                    }
                                    else -> {
                                        // Handle any other states if needed
                                    }
                                }
                            }
                        }

                        // Pager indicator
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp),
                            activeColor = MaterialTheme.colorScheme.primary,
                            inactiveColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Product Details
                Text(
                    text = product.pDescription,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Price and Stock
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$${product.pPrice}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "In stock: ${product.pQuantity}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Category
                Text(
                    text = "Category: ${product.pCategory.cName}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Offer
                if (product.pOffer.isNotEmpty()) {
                    Text(
                        text = "Special offer: ${product.pOffer}% off",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Add to Cart Button
                Button(
                    onClick = { /* TODO: Add to cart */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add to Cart")
                }
            }
        }
    }
}

fun String?.orZero(): Double = this?.toDoubleOrNull() ?: 0.0