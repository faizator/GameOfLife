package com.faz.gameoflife.interactor

import com.faz.gameoflife.storage.SettingsStorage
import javax.inject.Inject

class SettingsInteractor @Inject constructor(
    private val settingsStorage: SettingsStorage
) {

    fun setWidthSize(size: Int) = settingsStorage.setWidthSize(size)

    fun setHeightSize(size: Int) = settingsStorage.setHeightSize(size)

    fun getWidthSize() = settingsStorage.getWidthSize()

    fun getHeightSize() = settingsStorage.getHeightSize()

    fun convertSizeToPercent(size: Int) = 100 * (size - MIN_SIZE) / (MAX_SIZE - MIN_SIZE)

    fun convertPercentToSize(percent: Int) = (MIN_SIZE + (MAX_SIZE - MIN_SIZE) * percent / 100f).toInt()

    companion object {
        const val MIN_SIZE = 5
        const val MAX_SIZE = 20
    }
}