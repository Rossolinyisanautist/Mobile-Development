package com.auca.doodlerisserialmurderer.dummy

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.File
import java.util.*


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


    fun generateSampleDoodleFiles(context: Context) {
        for(i in 1..COUNT) {
            val path = createDoodleFile(context)
            ITEMS.add(Doodle(i.toString(), "Doodle $i", path))
        }
    }


    data class Doodle(val id: String, val name: String, val path: String) {
        override fun toString(): String = name
    }
}
