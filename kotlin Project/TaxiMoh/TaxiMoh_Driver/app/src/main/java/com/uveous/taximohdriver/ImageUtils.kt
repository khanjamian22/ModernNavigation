package com.uveous.taximohdriver

import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import java.io.ByteArrayOutputStream
import java.io.File


class ImageUtils{

    fun getBytesFromBitmap(bitmap: Bitmap): ByteArray? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream)
        return stream.toByteArray()
    }
    fun getBytesFromBitmapCamera(bitmap: Bitmap): ByteArray? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    
}