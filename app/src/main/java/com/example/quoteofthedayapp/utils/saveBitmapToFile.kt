package com.example.quoteofthedayapp.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String): Uri {
    val file = File(context.getExternalFilesDir(null), "$fileName.png")
    val outputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    outputStream.flush()
    outputStream.close()
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
}

fun shareImage(context: Context, uri: Uri) {
    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/png"
    }
    context.startActivity(Intent.createChooser(shareIntent, null))
}