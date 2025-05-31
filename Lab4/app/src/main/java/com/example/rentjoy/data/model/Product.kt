package com.example.rentjoy.data.model

data class Product(
    val _id: String,
    val pName: String,
    val pDescription: String,
    val pPrice: Double,
    val pQuantity: Int,
    val pSold: Int,
    val pOffer: String,
    val pImages: List<String>,
    val pStatus: String,
    val pCategory: Category,
    val pRatingsReviews: List<Any>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)

data class Category(
    val _id: String,
    val cName: String
)

data class ProductResponse(
    val Products: List<Product>
) 