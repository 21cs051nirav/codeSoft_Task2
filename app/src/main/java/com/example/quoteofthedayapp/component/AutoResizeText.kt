package com.example.quoteofthedayapp.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizeText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    maxLines: Int = 1,
    minTextSize: TextUnit = 12.sp,
    maxTextSize: TextUnit = 30.sp,
) {
    var textSize by remember { mutableStateOf(maxTextSize) }
    var readyToDraw by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Text(
            text = text,
            style = style.copy(fontSize = textSize),
            maxLines = maxLines,
            onTextLayout = { textLayoutResult ->
                if (!readyToDraw) {
                    if (textLayoutResult.didOverflowHeight) {
                        if (textSize > minTextSize) {
                            textSize = (textSize.value-1).sp
                        } else {
                            readyToDraw = true
                        }
                    } else {
                        readyToDraw = true
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}