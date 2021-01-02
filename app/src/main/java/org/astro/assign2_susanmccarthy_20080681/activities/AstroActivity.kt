package org.astro.assign2_susanmccarthy_20080681.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_astro.astroEventTitle
import kotlinx.android.synthetic.main.activity_astro.astroEventDescription
import kotlinx.android.synthetic.main.activity_astro_list.*
import kotlinx.android.synthetic.main.activity_astro.*
import kotlinx.android.synthetic.main.card_astro.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.astro.assign2_susanmccarthy_20080681.R
import org.astro.assign2_susanmccarthy_20080681.main.MainApp
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel
import org.jetbrains.anko.toast

class AstroActivity : AppCompatActivity(), AnkoLogger {

    var astroEvent = AstroModel()
   // val astroList = ArrayList<AstroModel>()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astro)
        app = application as MainApp

        if (intent.hasExtra("astroEvent_edit")) {
            astroEvent = intent.extras?.getParcelable<AstroModel>("astroEvent_edit")!!
            astroEventTitle.setText(astroEvent.title)
            astroEventDescription.setText(astroEvent.description)
        }

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        info("Astro Activity started...")

        btnAdd.setOnClickListener() {
            astroEvent.title = astroEventTitle.text.toString()
            astroEvent.description = astroEventDescription.text.toString()
            if (astroEvent.title.isNotEmpty() && astroEvent.description.isNotEmpty()) {
                app.astroList.create(astroEvent.copy())
                info("Add Button Pressed: $astroEvent")
//                for (i in app.astroList.indices) {
//                    info("Astro Event [$i]: ${app.astroList[i]}")
//                }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast ("Please enter an Event Title and Description")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}