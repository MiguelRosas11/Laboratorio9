package com.example.laboratorio9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio9.feature.profile.presentation.ProfileScreen
import com.example.laboratorio9.feature.wishlist.presentation.WishlistScreen
import com.example.laboratorio9.feature.wishlist.presentation.WishlistViewModel

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Wishlist.route,
        route = Destinations.Root.route
    ) {
        composable(Destinations.Wishlist.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.Root.route)
            }
            val vm: WishlistViewModel = viewModel(parentEntry)
            val uiState = vm.uiState.collectAsStateWithLifecycle().value

            WishlistScreen(
                state = uiState,
                onToggle = { id -> vm.toggleWishlist(id) },
                onGoToProfile = { navController.navigate(Destinations.Profile.route) },
                onLoadIfNeeded = { vm.loadProducts() }
            )
        }

        composable(Destinations.Profile.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Destinations.Root.route)
            }
            val vm: WishlistViewModel = viewModel(parentEntry)
            val count = vm.uiState.collectAsStateWithLifecycle().value.products.count { it.isWishlisted }

            ProfileScreen(
                wishlistedCount = count,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
