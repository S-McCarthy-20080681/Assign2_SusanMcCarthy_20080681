package org.astro.assign2_susanmccarthy_20080681

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_astro.view.*
import kotlinx.android.synthetic.main.card_astro.view.astroEventDescription
import kotlinx.android.synthetic.main.card_astro.view.astroEventTitle
import kotlinx.android.synthetic.main.card_astro.view.astroEventTime
import kotlinx.android.synthetic.main.card_astro.view.astroEventNext
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

        // display the Event title, Event values and Event Image in the Card View and expanded
        // Event View.
        fun bind(astroEvent: AstroModel, listener: AstroListener) {
            itemView.astroEventTitle.text = astroEvent.title
            itemView.astroEventDescription.text = astroEvent.description
            itemView.astroEventTime.text = astroEvent.closestTime
            itemView.astroEventNext.text = astroEvent.nextTime
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, astroEvent.image))
            itemView.setOnClickListener {listener.onAstroEventClick(astroEvent)}
        }
    }
}