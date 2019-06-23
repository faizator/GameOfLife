package com.faz.gameoflife.interactor

import com.faz.gameoflife.storage.PatternDatabase
import com.faz.gameoflife.storage.SettingsStorage
import io.reactivex.Completable
import javax.inject.Inject

class PatternSelectInteractor @Inject constructor(
    patternDatabase: PatternDatabase,
    private val settingsStorage: SettingsStorage
) {
    private val patternDao by lazy { patternDatabase.getPatternDao() }

    fun getAllPatterns() = patternDao.getAll()

    fun deletePatternById(id: Int) = Completable.fromAction {
        patternDao.deletePatternById(id)
    }

    fun setSelectedPatternId(id: Int) = settingsStorage.setSelectedPatternId(id)
}