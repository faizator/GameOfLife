package com.faz.gameoflife.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.faz.gameoflife.storage.entity.PatternEntity
import com.faz.gameoflife.storage.entity.PatternEntity.Companion.ID
import com.faz.gameoflife.storage.entity.PatternEntity.Companion.TABLE_NAME
import io.reactivex.Single

@Dao
interface PatternDao {

    @Insert(onConflict = REPLACE)
    fun insert(pattern: PatternEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Single<List<PatternEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID=:id")
    fun getById(id: Int): Single<PatternEntity>

    @Query("DELETE FROM $TABLE_NAME WHERE $ID=:id")
    fun deletePatternById(id: Int): Int

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    fun getCount(): Int
}