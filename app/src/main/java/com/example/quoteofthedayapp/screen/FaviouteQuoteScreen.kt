package com.example.quoteofthedayapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quoteofthedayapp.component.CardOfQuote
import com.example.quoteofthedayapp.viewModel.QuoteViewModel

@Composable
fun FavoriteQuoteScreen(navController: NavController,quoteViewModel: QuoteViewModel) {

    val list by quoteViewModel.listOfQuote.collectAsState()
     LaunchedEffect(key1 = Unit) {
         quoteViewModel.getFavouriteList()
     }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Your Favorite",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
        if(list.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No Favorite")
            }
        }else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(list) {
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .background(Color.Transparent)
                    ) {
                        CardOfQuote(quote = it) {
                            quoteViewModel.removeFromFavourite(it)
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                    ) {

                    }
                }
            }
        }
    }
}