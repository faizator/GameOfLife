package com.faz.gameoflife.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.faz.gameoflife.storage.dao.PatternDao
import com.faz.gameoflife.storage.entity.PatternEntity

@Database(entities = [PatternEntity::class], version = 1)
abstract class PatternDatabase : RoomDatabase() {

    abstract fun getPatternDao(): PatternDao

}