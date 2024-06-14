package com.example.quoteofthedayapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quoteofthedayapp.R
import com.example.quoteofthedayapp.component.AutoResizeText
import com.example.quoteofthedayapp.component.SelectCard
import com.example.quoteofthedayapp.data.QuoteData
import com.example.quoteofthedayapp.utils.HomeScreenState
import com.example.quoteofthedayapp.utils.shareText
import com.example.quoteofthedayapp.viewModel.QuoteViewModel

@Composable
fun HomeScreen(
    quoteViewModel: QuoteViewModel,
) {
    val homeScreenState by quoteViewModel.homeScreenState.collectAsState(initial = HomeScreenState.Loading)
     val context= LocalContext.current
    when (homeScreenState) {
        is HomeScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

            }
        }

        is HomeScreenState.Error -> {
            val error = (homeScreenState as HomeScreenState.Error).message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = error)
            }
        }

        else -> {
            val alreadyInDB by quoteViewModel.isInDB.collectAsState()

            val quote = (homeScreenState as HomeScreenState.Success).quote ?: QuoteData(
                id = "",
                "No", "--",
                emptyList()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 14.dp)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Quote's",
                        style = TextStyle(
                            fontSize = 34.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Tag : ",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                    ) {

                        items(quote.tags.size) {
                            Box(
                                modifier = Modifier.padding(2.dp)
                            ) {
                                SelectCard(title = quote.tags[it])
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(5f),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Card(
                        elevation = CardDefaults.elevatedCardElevation(6.dp),
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .fillMaxHeight(.6f),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                                    AutoResizeText(
                                        text = "\"" + quote.content + "\"",
                                        style = TextStyle(
                                            fontSize = 26.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            fontStyle = FontStyle.Italic,
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            textAlign = TextAlign.Center
                                        ),
                                        maxLines = 10,
                                        modifier = Modifier.weight(3f)
                                    )
                                    Text(
                                        text = "~${quote.author}~",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontStyle = FontStyle.Italic,
                                            color = MaterialTheme.colorScheme.onPrimary
                                        ),
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                IconButton(
                                    onClick = {
                                        quoteViewModel.change()
                                        if(!alreadyInDB){
                                        quoteViewModel.addToFavouriteList(quote)
                                        }else{
                                            quoteViewModel.removeFromFavourite(quote)
                                        }
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    if (alreadyInDB) Icon(
                                        imageVector = Icons.Outlined.Favorite,
                                        contentDescription = ""
                                    )
                                    else Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = ""
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        shareText(context,"\"${quote.content}\" by ${quote.author}")
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.rounded_ios_share_24),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    }
                }


            }
        }
    }
}