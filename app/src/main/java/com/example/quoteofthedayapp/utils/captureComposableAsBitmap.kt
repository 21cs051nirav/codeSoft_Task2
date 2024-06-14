package com.example.quoteofthedayapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.platform.ComposeView

fun captureComposableAsBitmap(context: Context, composable: @Composable () -> Unit): Bitmap {
    val composeView = ComposeView(context).apply {
        setContent {
            composable()
        }
    }

    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    composeView.layout(0, 0, composeView.measuredWidth, composeView.measuredHeight)

    val bitmap = Bitmap.createBitmap(
        composeView.width,
        composeView.height,
        Bitmap.Config.ARGB_8888
    )
    val canvas = android.graphics.Canvas(bitmap)
    composeView.draw(canvas)

    return bitmap
}
