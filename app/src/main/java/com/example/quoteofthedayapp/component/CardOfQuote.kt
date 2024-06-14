package com.example.quoteofthedayapp.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.quoteofthedayapp.data.QuoteData
import com.example.quoteofthedayapp.utils.shareText
import kotlin.text.Typography.quote

@Composable
fun CardOfQuote(quote: QuoteData,onClick:()->Unit) {
    Log.d("CardCard", "CardOfQuote: $quote")
      var add by remember {
          mutableStateOf(true)
      }
    val context= LocalContext.current
        Card(
            elevation = CardDefaults.elevatedCardElevation(6.dp),
            modifier = Modifier.fillMaxWidth().height(200.dp),
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
                            add=!add
                            onClick()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        if (add) Icon(
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