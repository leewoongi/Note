package com.woongi.home.model.mapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun Uri.uriToImageBitmap(context: Context): ImageBitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(context.contentResolver, this)
        val bitmap = ImageDecoder.decodeBitmap(source)
        bitmap.asImageBitmap()
    } else {
        val inputStream = context.contentResolver.openInputStream(this)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        bitmap.asImageBitmap()
    }
}

fun resizeImageBitmap(bitmap: ImageBitmap, targetWidth: Int, targetHeight: Int): ImageBitmap {
    val originalBitmap = bitmap.asAndroidBitmap()

    val scaledBitmap = Bitmap.createScaledBitmap(
        originalBitmap,
        targetWidth,
        targetHeight,
        true
    )

    return scaledBitmap.asImageBitmap()
}