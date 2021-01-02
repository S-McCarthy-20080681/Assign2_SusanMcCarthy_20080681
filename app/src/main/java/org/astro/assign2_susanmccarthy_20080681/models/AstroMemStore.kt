package org.astro.assign2_susanmccarthy_20080681.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class AstroMemStore: AstroStore, AnkoLogger {

    val astroList = ArrayList<AstroModel>()

    override fun findAll(): List<AstroModel> {
        return astroList
    }

    //gives the new event a unique ID as it is created
    override fun create(astroEvent: AstroModel) {
        astroEvent.id = getId()
        astroList.add(astroEvent)
        logAll()
    }

    // identifying the found event by its ID, which is mapped to the letter 'l', and setting the
    // 'found' event's existing values to be the event values entered by the user.
    override fun update(astroEvent: AstroModel) {
        var foundAstroEvent: AstroModel? = astroList.find { l -> l.id == astroEvent.id }
        if (foundAstroEvent != null) {
            foundAstroEvent.title = astroEvent.title
            foundAstroEvent.description = astroEvent.description
            foundAstroEvent.image = astroEvent.image
            logAll()
        }
    }

    override fun delete(astroEvent: AstroModel) {
        astroList.remove(astroEvent)
    }

    fun logAll() {
        astroList.forEach{ info("${it}") }
    }

}