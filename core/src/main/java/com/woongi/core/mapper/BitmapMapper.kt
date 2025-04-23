package com.woongi.core.mapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun Uri.uriToImageBitmap(context: Context): ImageBitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(context.contentResolver, this)
        val bitmap = ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
        }
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

fun toGrayscaleBitmap(original: Bitmap): Bitmap {
    val width = original.width
    val height = original.height
    val grayscaleBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    val canvas = android.graphics.Canvas(grayscaleBitmap)
    val paint = android.graphics.Paint()

    val colorMatrix = android.graphics.ColorMatrix()
    colorMatrix.setSaturation(0f) // <- 포인트: 채도를 0으로 만들면 흑백됨

    val filter = android.graphics.ColorMatrixColorFilter(colorMatrix)
    paint.colorFilter = filter

    canvas.drawBitmap(original, 0f, 0f, paint)
    return grayscaleBitmap
}

fun toGrayscaleImageBitmap(original: ImageBitmap): ImageBitmap {
    val androidBitmap = original.asAndroidBitmap()
    val grayBitmap = toGrayscaleBitmap(androidBitmap)
    return grayBitmap.asImageBitmap()
}