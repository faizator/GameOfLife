package com.faz.gameoflife.storage.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.faz.gameoflife.storage.entity.PatternEntity.Companion.TABLE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = TABLE_NAME)
data class PatternEntity(
    @ColumnInfo(name = ID) @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = NAME) var name: String,
    @ColumnInfo(name = DATA) var data: String
) {

    companion object {
        const val TABLE_NAME = "pattern"
        const val ID = "id"
        const val NAME = "name"
        const val DATA = "data"
        private val gson = Gson()
        private val listType = object : TypeToken<MutableList<MutableList<Int>>>() {}.type

        fun convertDataToString(data: MutableList<MutableList<Int>>): String = gson.toJson(data)

        fun convertStringToData(dataString: String): MutableList<MutableList<Int>> = gson.fromJson<MutableList<MutableList<Int>>>(dataString, listType)
    }
}
