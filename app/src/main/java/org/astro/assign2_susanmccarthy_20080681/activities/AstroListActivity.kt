package org.astro.assign2_susanmccarthy_20080681

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_astro_list.*
import org.astro.assign2_susanmccarthy_20080681.activities.*
import org.astro.assign2_susanmccarthy_20080681.main.MainApp
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

class AstroListActivity : AppCompatActivity(), AstroListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astro_list)
        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = AstroAdapter(app.astroList.findAll(), this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<AstroActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAstroEventClick(astroEvent: AstroModel) {
        startActivityForResult(intentFor<AstroActivity>(), 0)
    }

}