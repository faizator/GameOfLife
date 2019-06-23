package com.faz.gameoflife.interactor

import com.faz.gameoflife.storage.PatternDatabase
import com.faz.gameoflife.storage.SettingsStorage
import com.faz.gameoflife.storage.entity.PatternEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GridInteractor @Inject constructor(
    patternDatabase: PatternDatabase,
    private val settingsStorage: SettingsStorage
) {

    private val patternDao by lazy { patternDatabase.getPatternDao() }

    var data = mutableListOf<MutableList<Int>>()
        private set

    fun reloadSelected(): Single<PatternEntity> {
        val selectedPatternId = settingsStorage.getSelectedPatternId()
        return patternDao.getById(selectedPatternId)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                data = PatternEntity.convertStringToData(it.data)
            }
    }

    fun save(name: String) = Completable.fromAction {
        patternDao.insert(
            PatternEntity(
                name = name,
                data = PatternEntity.convertDataToString(data)
            )
        )
    }
        .subscribeOn(Schedulers.single())
        .observeOn(AndroidSchedulers.mainThread())

    fun clear() {
        val widthSize = settingsStorage.getWidthSize()
        val heightSize = settingsStorage.getHeightSize()
        data = MutableList(heightSize) { MutableList(widthSize) { 0 } }
    }

    fun toggleCell(row: Int, col: Int) {
        data[row][col] = if (data[row][col] == 0) 1 else 0
    }

    fun step() {
        if (data.isEmpty()) return
        val newData = mutableListOf<MutableList<Int>>()
        val rowCount = data.size
        val colCount = data[0].size
        for (i in 0 until rowCount) {
            newData.add(MutableList(colCount) { 0 })
            for (j in 0 until colCount) {
                val aliveAroundCount = calculateAliveAround(i, j, rowCount, colCount)
                newData[i][j] = when (aliveAroundCount) {
                    2 -> data[i][j]
                    3 -> 1
                    else -> 0
                }
            }
        }

        data = newData
    }

    private fun calculateAliveAround(row: Int, col: Int, rowCount: Int, colCount: Int): Int {
        var aliveAroundCount = 0
        for (i in row - 1..row + 1) {
            for (j in col - 1..col + 1) {
                if (i == row && j == col) {
                    continue
                }
                val normalizedI = when {
                    i < 0 -> rowCount + i
                    i > rowCount - 1 -> 0
                    else -> i
                }
                val normalizedJ = when {
                    j < 0 -> colCount + j
                    j > colCount - 1 -> 0
                    else -> j
                }
                if (data[normalizedI][normalizedJ] == 1) {
                    aliveAroundCount++
                }
            }
        }
        return aliveAroundCount
    }
}