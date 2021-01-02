package org.astro.assign2_susanmccarthy_20080681

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_astro.view.*
import org.astro.assign2_susanmccarthy_20080681.R
import org.astro.assign2_susanmccarthy_20080681.helpers.readImageFromPath
import org.astro.assign2_susanmccarthy_20080681.models.AstroModel

interface AstroListener {
    fun onAstroEventClick(astroEvent: AstroModel)
}

class AstroAdapter constructor(private var astroList: List<AstroModel>,
                                private val listener: AstroListener) :
        RecyclerView.Adapter<AstroAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_astro,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val astroEvent = astroList[holder.adapterPosition]
        holder.bind(astroEvent, listener)
    }

    override fun getItemCount(): Int = astroList.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // display the Event title, Event values and Event Image in the List
        fun bind(astroEvent: AstroModel, listener: AstroListener) {
            itemView.astroEventTitle.text = astroEvent.title
            itemView.astroEventDescription.text = astroEvent.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, astroEvent.image))
            itemView.setOnClickListener {listener.onAstroEventClick(astroEvent)}
        }
    }
}