package org.astro.assign2_susanmccarthy_20080681.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AstroModel(var title: String = "",
                      var description: String = "") : Parcelable
