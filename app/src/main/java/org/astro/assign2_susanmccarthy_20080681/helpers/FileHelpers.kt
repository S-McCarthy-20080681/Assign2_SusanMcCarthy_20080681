package org.astro.assign2_susanmccarthy_20080681.helpers

import android.content.Context
import android.util.Log
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

// function to write any data in the app to a save file
fun write(context: Context, fileName: String, data: String) {
    try {
        val outputStreamWriter = OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        Log.e("Error: ", "Cannot Read File: " + e.toString())
    }
}

// function to read any data from a save file back into the app again
fun read(context: Context, fileName: String): String {
    var str = ""
    try {
        val inputStream = context.openFileInput(fileName)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                var line = bufferedReader.readLine()
                done = (line == null)
                if (line != null) partialStr.append(line)
            }
            inputStream.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        Log.e("Error: ", "File Not Found: " + e.toString())
    } catch (e: IOException) {
        Log.e("Error: ", "Cannot Read File: " + e.toString())
    }
    return str
}

// function to check whether or not a save file exists
fun exists(context: Context, fileName: String): Boolean {
    val file = context.getFileStreamPath(fileName)
    return file.exists()
}