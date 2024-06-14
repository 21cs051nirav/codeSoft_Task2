package com.example.quoteofthedayapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quoteofthedayapp.component.CardOfQuote
import com.example.quoteofthedayapp.viewModel.QuoteViewModel

@Composable
fun FavoriteQuoteScreen(navController: NavController,quoteViewModel: QuoteViewModel) {

    val list by quoteViewModel.listOfQuote.collectAsState()
     LaunchedEffect(key1 = Unit) {
         quoteViewModel.getFavouriteList()
     }
     LazyColumn(
         modifier = Modifier
             .fillMaxSize()
             .padding(12.dp),
     ) {
         item {
             Box (modifier = Modifier
                 .fillMaxWidth()
                 .height(120.dp)){

             }
         }
         items(list){
             CardOfQuote(quote = it){
                  quoteViewModel.removeFromFavourite(it)
             }
             Spacer(modifier = Modifier.height(6.dp))
         }
         item {
             Box (modifier = Modifier
                 .fillMaxWidth()
                 .height(75.dp)){

             }
         }
     }
}