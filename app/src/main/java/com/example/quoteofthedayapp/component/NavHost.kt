package com.example.quoteofthedayapp.component

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quoteofthedayapp.screen.FavoriteQuoteScreen
import com.example.quoteofthedayapp.screen.HomeScreen
import com.example.quoteofthedayapp.viewModel.QuoteViewModel

@Composable
fun NavigationHost(
    navController: NavHostController,
    quoteViewModel:QuoteViewModel,
    context: Context,
    padding:PaddingValues
){
    NavHost(
        startDestination = BottomBarScreen.Home.route.toString(),
        navController = navController
    ){
        composable(BottomBarScreen.Home.route.toString()){
            HomeScreen(quoteViewModel =quoteViewModel,context )
        }
        composable(BottomBarScreen.History.route.toString()){
            FavoriteQuoteScreen(navController = navController, quoteViewModel =quoteViewModel )
        }
    }
}