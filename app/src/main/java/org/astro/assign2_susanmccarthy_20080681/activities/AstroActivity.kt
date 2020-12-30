package org.astro.assign2_susanmccarthy_20080681.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_astro.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.astro.assign2_susanmccarthy_20080681.R
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel
import org.jetbrains.anko.toast

class AstroActivity : AppCompatActivity(), AnkoLogger {

    var astroEvent = AstroModel()
    val astroList = ArrayList<AstroModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astro)
        info("Astro Activity started...")

        btnAdd.setOnClickListener() {
            astroEvent.title = astroEventTitle.text.toString()
            if (astroEvent.title.isNotEmpty()) {
                info("Add Button Pressed: $astroEvent")
            } else {
                toast ("Please enter an Event Title")
            }
        }
    }
}