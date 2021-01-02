package org.astro.assign2_susanmccarthy_20080681.activities

import android.content.Intent
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
import org.astro.assign2_susanmccarthy_20080681.helpers.readImage
import org.astro.assign2_susanmccarthy_20080681.helpers.readImageFromPath
import org.astro.assign2_susanmccarthy_20080681.helpers.showImagePicker
import org.astro.assign2_susanmccarthy_20080681.main.MainApp
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel
import org.jetbrains.anko.toast

class AstroActivity : AppCompatActivity(), AnkoLogger {

    var astroEvent = AstroModel()
    val IMAGE_REQUEST = 1
   // val astroList = ArrayList<AstroModel>()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astro)
        app = application as MainApp

        var edit = false

        if (intent.hasExtra("astroEvent_edit")) {
            edit = true
            astroEvent = intent.extras?.getParcelable<AstroModel>("astroEvent_edit")!!
            astroEventTitle.setText(astroEvent.title)
            astroEventDescription.setText(astroEvent.description)
            btnAdd.setText(R.string.save_update)
            astroEventImage.setImageBitmap(readImageFromPath(this, astroEvent.image))
        }

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        info("Astro Activity started...")

        btnAdd.setOnClickListener {
            astroEvent.title = astroEventTitle.text.toString()
            astroEvent.description = astroEventDescription.text.toString()
            if (astroEvent.title.isEmpty()) {
                toast(R.string.enter_event_values)
            } else {
                if (edit) {
                    app.astroList.update(astroEvent.copy())
                } else {
                    app.astroList.create(astroEvent.copy())
                }
            }
            info("Add Button Pressed: $astroEventTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
            info("Select an image...")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    astroEvent.image = data.getData().toString()
                    astroEventImage.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
    }

}