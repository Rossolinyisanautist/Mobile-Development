package com.auca.final_2.dummy

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.File
import java.io.Serializable
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {
    val ITEMS: MutableList<Doodle> = ArrayList()


    private val COUNT = 25

    fun createDoodleFile(context: Context) : String {
        val doodleDir = context.getDir("doodles", MODE_PRIVATE)
        val path = "${doodleDir.absoluteFile}${File.separator}${UUID.randomUUID()}.webp"
        val doodleFile = File(path)
        doodleFile.createNewFile()

        return path

    }

    fun generateSampleDoodles(context: Context) {
        for (i in 1 .. COUNT) {
            val path = createDoodleFile(context)
            ITEMS.add(Doodle(i.toString(), "Doodel $i", path))
        }
    }

    data class Doodle(val id: String, val name: String, val path: String) : Serializable {
        override fun toString(): String = name
    }
}
