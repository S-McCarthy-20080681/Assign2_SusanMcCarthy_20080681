package org.astro.assign2_susanmccarthy_20080681.main

import android.app.Application
import org.astro.assign2_susanmccarthy_20080681.models.AstroMemStore
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {

    //val astroList = ArrayList<AstroModel>()
    val astroList = AstroMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Astro App Started")
        astroList.create(AstroModel("Lunar Eclipse", "About Lunar Eclipses"))
        astroList.create(AstroModel("Ursids", "December meteor shower"))
        astroList.create(AstroModel("The Great Conjunction", "The alignment of Jupiter and Saturn"))
    }

}