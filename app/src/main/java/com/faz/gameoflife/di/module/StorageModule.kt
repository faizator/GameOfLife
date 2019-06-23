package com.faz.gameoflife.di.module

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.faz.gameoflife.storage.PatternDatabase
import com.faz.gameoflife.storage.PatternDatabaseInitializer
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun sharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun getPatternsDatabase(context: Context, patternDatabaseInitializer: PatternDatabaseInitializer) =
        Room.databaseBuilder(context, PatternDatabase::class.java, "pattern_database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    patternDatabaseInitializer.initialize()
                }
            })
            .build()
}