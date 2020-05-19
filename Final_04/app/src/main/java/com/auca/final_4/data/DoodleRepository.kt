package com.auca.final_4.data

import androidx.lifecycle.LiveData

class DoodleRepository(private val doodleDao: DoodleDao) {
    val allDoodles: LiveData<List<Doodle>> = doodleDao.getDoodles()

    suspend fun insert(doodle: Doodle) {
        doodleDao.insert(doodle)
    }

    suspend fun delete(doodle: Doodle) {
        doodleDao.delete(doodle)
    }
}