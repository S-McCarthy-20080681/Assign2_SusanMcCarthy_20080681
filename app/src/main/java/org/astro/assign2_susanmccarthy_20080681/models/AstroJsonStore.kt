package org.astro.assign2_susanmccarthy_20080681.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.astro.assign2_susanmccarthy_20080681.helpers.*
import java.util.*
import kotlin.collections.ArrayList

// SAVE FILE NAME:
val JSON_FILE = "astroEvents.json"

val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<AstroModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class AstroJsonStore: AstroStore, AnkoLogger {

    val context: Context
    var astroList = mutableListOf<AstroModel>()

    // read the save file at runtime
    constructor(context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    // return the full contents of the List
    override fun findAll(): MutableList<AstroModel> {
        return astroList
    }

    // create an event, and write the change to the save file
    override fun create(astroEvent: AstroModel) {
        astroEvent.id = generateRandomId()
        astroList.add(astroEvent)
        serialize()
    }

    // update an event, and write the change to the save file
    override fun update(astroEvent: AstroModel) {
        val astroList = findAll() as ArrayList<AstroModel>
        var foundAstroEvent: AstroModel? = astroList.find { l -> l.id == astroEvent.id }
        if (foundAstroEvent != null) {
            foundAstroEvent.title = astroEvent.title
            foundAstroEvent.description = astroEvent.description
            foundAstroEvent.image = astroEvent.image
        }
        serialize()
    }

    // delete an event, and write the change to the save file
    override fun delete(astroEvent: AstroModel) {
        astroList.remove(astroEvent)
        serialize()
    }

    // writes all changes to save file
    private fun serialize() {
        val jsonString = gsonBuilder.toJson(astroList, listType)
        write(context, JSON_FILE, jsonString)
    }

    // builds app data from save file
    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        astroList = Gson().fromJson(jsonString, listType)
    }
}