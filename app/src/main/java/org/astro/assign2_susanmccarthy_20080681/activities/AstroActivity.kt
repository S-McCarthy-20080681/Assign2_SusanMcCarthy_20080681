package org.astro.assign2_susanmccarthy_20080681.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_astro.astroEventTitle
import kotlinx.android.synthetic.main.activity_astro.astroEventDescription
import kotlinx.android.synthetic.main.activity_astro.astroEventTime
import kotlinx.android.synthetic.main.activity_astro.astroEventNext
import kotlinx.android.synthetic.main.activity_astro.*
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

    lateinit var app: MainApp

    // the app is called and run from the MainApp class, and all app functions are managed
    // under this function.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astro)
        app = application as MainApp

        var edit = false

        // if statement to handle the Update feature; if an Event is clicked on, its values
        // are filled in so a user can change or keep what they want.
        if (intent.hasExtra("astroEvent_edit")) {
            edit = true
            astroEvent = intent.extras?.getParcelable<AstroModel>("astroEvent_edit")!!
            astroEventTitle.setText(astroEvent.title)
            astroEventDescription.setText(astroEvent.description)
            astroEventTime.setText(astroEvent.closestTime)
            astroEventNext.setText(astroEvent.nextTime)
            btnAdd.setText(R.string.save_update)
            astroEventImage.setImageBitmap(readImageFromPath(this, astroEvent.image))
            if (astroEvent.image != null) {
                chooseImage.setText(R.string.change_image)
            }
        }

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        info("Astro Activity started...")

        // function to write user input to each value when Add Event button is pushed.
        // Validation in place to stop the function if user input is not there, and to
        // call either the Update or Create features accordingly.
        btnAdd.setOnClickListener {
            astroEvent.title = astroEventTitle.text.toString()
            astroEvent.description = astroEventDescription.text.toString()
            astroEvent.closestTime = astroEventTime.text.toString()
            astroEvent.nextTime = astroEventNext.text.toString()
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

        // call the showImagePicker function (which allows a user to select an image from their
        // gallery) when the Choose Image button is pressed.
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
            info("Select an image...")
        }

    }

    // function to call the buttons along the action bar at this point, i.e. Delete and Cancel.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // function to call the DELETE feature when the Delete button is pushed, and to
    // return to the Main Page when the Cancel button is pushed.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.astroList.delete(astroEvent)
                finish()
        }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // function to call the current image saved for an Event, and offer the user a chance
    // to change it.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    astroEvent.image = data.getData().toString()
                    astroEventImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_image)
                }
            }
        }
    }

}