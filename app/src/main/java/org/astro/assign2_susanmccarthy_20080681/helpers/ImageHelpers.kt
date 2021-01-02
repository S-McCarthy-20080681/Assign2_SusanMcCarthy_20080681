package org.astro.assign2_susanmccarthy_20080681.helpers

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import org.astro.assign2_susanmccarthy_20080681.R
import java.io.IOException

// function to OPEN THE IMAGE GALLERY
fun showImagePicker(parent: Activity, id: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_OPEN_DOCUMENT
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    val chooser = Intent.createChooser(intent, R.string.select_event_image.toString())
    parent.startActivityForResult(chooser, id)
}

// function to actually READ AN IMAGE FROM THE GALLERY INTO THE APP
fun readImage(activity: Activity, resultCode: Int, data: Intent?): Bitmap? {
    var bitmap: Bitmap? = null
    if (resultCode == Activity.RESULT_OK && data != null && data.data != null) {
        try {
            bitmap = ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(activity.contentResolver, data.data!!))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return bitmap
}