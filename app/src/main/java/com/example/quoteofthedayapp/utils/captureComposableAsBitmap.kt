package com.example.quoteofthedayapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.platform.ComposeView

fun captureComposableAsBitmap(
    context: Context,
    composable: @Composable () -> Unit,
    onBitmapCaptured: (Bitmap) -> Unit
) {
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
    // Create bitmap and canvas
    val bitmap = Bitmap.createBitmap(
        composeView.measuredWidth,
        composeView.measuredHeight,
        Bitmap.Config.ARGB_8888
    )
    // Dismiss the dialog and pass the bitmap
    onBitmapCaptured(bitmap)

}
