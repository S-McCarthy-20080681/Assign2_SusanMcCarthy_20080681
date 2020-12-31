package org.astro.assign2_susanmccarthy_20080681.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class AstroMemStore: AstroStore, AnkoLogger {

    val astroList = ArrayList<AstroModel>()

    override fun findAll(): List<AstroModel> {
        return astroList
    }

    override fun create(astroEvent: AstroModel) {
        astroList.add(astroEvent)
        logAll()
    }

    fun logAll() {
        astroList.forEach{ info("${it}") }
    }

}