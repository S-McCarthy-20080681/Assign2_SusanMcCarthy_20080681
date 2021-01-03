package org.astro.assign2_susanmccarthy_20080681

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
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

//        val searchResult = findViewById<ListView>(R.id.searchResults)
//        val searchBar = findViewById<MaterialSearchBar>(R.id.searchView)

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadAstroEvents()

        //val searchArray = ArrayList<AstroModel>()
        //searchResult.searchAdapter

//        searchBar.addTextChangeListener(object : TextWatcher {
//            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
//                //searchAdapter.filter(charSequence)
//                searchResult.textFilter.filter { true }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                TODO("Not yet implemented")
//            }
//        })

//        searchResult.setOnItemClickListener(object : AdapterView.OnItemClickListener {
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                Toast.makeText(this@AstroListActivity, searchAdapter.getItem)
//            }
//        })

    }

    // return all events currently in the app.
    private fun loadAstroEvents() {
        showAstroEvents(app.astroList.findAll())
    }

    // function to notify the recycler view that some data has changed and to refresh itself
    // to reflect this.
    fun showAstroEvents(astroList: List<AstroModel>) {
        recyclerView.adapter = AstroAdapter(astroList, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    // function to call the buttons along the action bar at this point, i.e. the 'Add' icon.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // function to tell the app to run the Create View when the 'Add' icon is pressed.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<AstroActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    // function to tell the app to open its Edit View when an individual Event is clicked on.
    override fun onAstroEventClick(astroEvent: AstroModel) {
        startActivityForResult(intentFor<AstroActivity>().putExtra("astroEvent_edit", astroEvent), 0)
    }

    // all Events currently saved in the app are displayed to the user.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadAstroEvents()
        super.onActivityResult(requestCode, resultCode, data)
    }

}