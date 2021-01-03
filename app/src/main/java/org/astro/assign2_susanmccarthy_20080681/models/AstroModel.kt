package org.astro.assign2_susanmccarthy_20080681.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class AstroModel(var id: Long = 0,
                      var title: String = "",
                      var description: String = "",
                      var closestTime: String = "",
                      var nextTime: String = "",
                      var image: String = "") : Parcelable
