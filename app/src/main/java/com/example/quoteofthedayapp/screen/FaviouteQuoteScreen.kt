package com.example.quoteofthedayapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.quoteofthedayapp.viewModel.QuoteViewModel

@Composable
fun FavoriteQuoteScreen(navController: NavController,quoteViewModel: QuoteViewModel) {
    
//    LaunchedEffect(key1 = Unit) {
//        quoteViewModel.getFavouriteList()
//    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Fav")
    }
}