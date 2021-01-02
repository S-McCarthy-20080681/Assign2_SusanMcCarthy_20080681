package org.astro.assign2_susanmccarthy_20080681.helpers

import android.app.Activity
import android.content.Intent
import org.astro.assign2_susanmccarthy_20080681.R

fun showImagePicker(parent: Activity, id: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_OPEN_DOCUMENT
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    val chooser = Intent.createChooser(intent, R.string.select_event_image.toString())
    parent.startActivityForResult(chooser, id)
}