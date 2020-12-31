package org.astro.assign2_susanmccarthy_20080681.main

import android.app.Application
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {

    val astroList = ArrayList<AstroModel>()

    override fun onCreate() {
        super.onCreate()
        info("Astro App Started")
        astroList.add(AstroModel("Lunar Eclipse", "About Lunar Eclipses"))
        astroList.add(AstroModel("Ursids", "December meteor shower"))
        astroList.add(AstroModel("The Great Conjunction", "The alignment of Jupiter and Saturn"))
    }

}