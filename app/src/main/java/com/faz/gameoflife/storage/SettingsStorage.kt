package com.faz.gameoflife.storage

import android.annotation.SuppressLint
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun setSelectedPatternId(id: Int) = sharedPreferences.edit { putInt(SELECTED_PATTERN_ID, id) }

    fun getSelectedPatternId() = sharedPreferences.getInt(SELECTED_PATTERN_ID, 1)

    fun setWidthSize(size: Int) = sharedPreferences.edit { putInt(WIDTH_SIZE_KEY, size) }

    fun setHeightSize(size: Int) = sharedPreferences.edit { putInt(HEIGHT_SIZE_KEY, size) }

    fun getWidthSize() = sharedPreferences.getInt(WIDTH_SIZE_KEY, 10)

    fun getHeightSize() = sharedPreferences.getInt(HEIGHT_SIZE_KEY, 10)

    @SuppressLint("ApplySharedPref")
    inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        action(editor)
        editor.apply()
    }

    companion object {
        const val SELECTED_PATTERN_ID = "SELECTED_PATTERN_ID"
        const val WIDTH_SIZE_KEY = "WIDTH_SIZE_KEY"
        const val HEIGHT_SIZE_KEY = "HEIGHT_SIZE_KEY"
    }
}