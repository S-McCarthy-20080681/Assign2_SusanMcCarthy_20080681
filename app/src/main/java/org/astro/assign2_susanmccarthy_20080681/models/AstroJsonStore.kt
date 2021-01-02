package org.astro.assign2_susanmccarthy_20080681.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.astro.assign2_susanmccarthy_20080681.helpers.*
import java.util.*

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

    constructor(context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AstroModel> {
        return astroList
    }

    override fun create(astroEvent: AstroModel) {
        astroEvent.id = generateRandomId()
        astroList.add(astroEvent)
        serialize()
    }

    override fun update(astroEvent: AstroModel) {
        TODO("Not yet implemented")
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(astroList, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        astroList = Gson().fromJson(jsonString, listType)
    }
}