package com.example.quoteofthedayapp.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quoteofthedayapp.R

@Composable
fun SelectCard(
    title:String,
) {
    AssistChip(
        onClick = {},
        label = {
            Text(
                title,
                color = MaterialTheme.colorScheme.onSurface
            )
        } ,
        leadingIcon = {
            Icon(
               painter = painterResource(id =  R.drawable.baseline_tag_24),
                contentDescription = "Localized description",
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                tint =MaterialTheme.colorScheme.onSurface
            )
        },
        modifier = Modifier.width(110.dp)
    )
}
