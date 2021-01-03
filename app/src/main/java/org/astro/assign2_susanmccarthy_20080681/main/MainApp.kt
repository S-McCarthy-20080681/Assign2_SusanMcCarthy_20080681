package org.astro.assign2_susanmccarthy_20080681.main

import android.app.Application
import org.astro.assign2_susanmccarthy_20080681.models.AstroJsonStore
import org.astro.assign2_susanmccarthy_20080681.models.AstroStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {

    lateinit var astroList: AstroStore

    // call the data stored by the AstroJSONStore and write it to the astroList array, which
    // will be returned to the user via the app view.
    override fun onCreate() {
        super.onCreate()
        astroList = AstroJsonStore(applicationContext)
        info("Astro App Started")

        // DUMMY DATA
        //astroList.create(AstroModel(0,"Lunar Eclipse", "About Lunar Eclipses"))
        //astroList.create(AstroModel(1,"Ursids", "December meteor shower"))
        //astroList.create(AstroModel(2,"The Great Conjunction", "The alignment of Jupiter and Saturn"))
    }

}