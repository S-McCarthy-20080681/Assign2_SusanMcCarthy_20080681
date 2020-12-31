package org.astro.assign2_susanmccarthy_20080681

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_astro_list.*
import kotlinx.android.synthetic.main.card_astro.view.*
import org.astro.assign2_susanmccarthy_20080681.activities.*
import org.astro.assign2_susanmccarthy_20080681.main.MainApp
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel
import org.jetbrains.anko.startActivityForResult

class AstroListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astro_list)
        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = AstroAdapter(app.astroList)
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

}

//class AstroAdapter constructor(private var astroList: List<AstroModel>) :
//        RecyclerView.Adapter<AstroAdapter.MainHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
//        return MainHolder(
//                LayoutInflater.from(parent.context).inflate(
//                        R.layout.card_astro,
//                        parent,
//                        false
//                )
//        )
//    }
//
//    override fun onBindViewHolder(holder: MainHolder, position: Int) {
//        val astroEvent = astroList[holder.adapterPosition]
//        holder.bind(astroEvent)
//    }
//
//    override fun getItemCount(): Int = astroList.size
//
//    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        fun bind(astroEvent: AstroModel) {
//            itemView.astroEventTitle.text = astroEvent.title
//            itemView.astroEventDescription.text = astroEvent.description
//        }
//    }
//}