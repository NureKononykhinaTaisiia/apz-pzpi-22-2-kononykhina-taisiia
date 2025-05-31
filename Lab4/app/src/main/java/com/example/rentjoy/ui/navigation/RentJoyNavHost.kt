package com.example.rentjoy.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rentjoy.data.model.Product
import com.example.rentjoy.ui.screens.*
import com.example.rentjoy.ui.viewmodel.ProductViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rentjoy.ui.screens.LoginScreen
import com.example.rentjoy.ui.screens.RegisterScreen
import com.example.rentjoy.ui.viewmodel.AuthViewModel

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val PRODUCT_LIST = "product_list"
    const val PRODUCT_DETAIL = "product_detail/{productId}"
    const val CART = "cart"
    const val WISHLIST = "wishlist"
    const val ORDER = "order"
    const val PROFILE = "profile"
}

@Composable
fun RentJoyNavHost(
    navController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory
) {
    val productViewModel: ProductViewModel = viewModel()
    val products by productViewModel.products.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = Modifier
    ) {
        composable(Routes.LOGIN) {
            val viewModel = androidx.lifecycle.viewmodel.compose.viewModel<AuthViewModel>(
                factory = viewModelFactory
            )
            LoginScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Routes.REGISTER) {
            val viewModel = androidx.lifecycle.viewmodel.compose.viewModel<AuthViewModel>(
                factory = viewModelFactory
            )
            RegisterScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Routes.PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }
        composable(
            route = "${Routes.PRODUCT_DETAIL}/{productId}",
            arguments = listOf(
                navArgument("productId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val product = products.find { it._id == productId }
            product?.let {
                ProductDetailScreen(navController, it)
            }
        }
        composable(Routes.CART) {
            CartScreen(navController)
        }
        composable(Routes.WISHLIST) {
            WishlistScreen(navController)
        }
        composable(Routes.ORDER) {
            OrderScreen(navController)
        }
        composable(Routes.PROFILE) {
            ProfileScreen(navController)
        }
    }
} 