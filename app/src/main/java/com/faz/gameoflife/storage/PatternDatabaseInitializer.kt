package com.faz.gameoflife.storage

import android.annotation.SuppressLint
import com.faz.gameoflife.storage.entity.PatternEntity
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider

class PatternDatabaseInitializer @Inject constructor(
    databaseProvider: Provider<PatternDatabase>
) {

    private val dao by lazy { databaseProvider.get().getPatternDao() }

    @SuppressLint("CheckResult")
    fun initialize() {
        Completable.fromAction {
            dao.insert(
                PatternEntity(
                    name = "Pulsar",
                    data = PatternEntity.convertDataToString(pulsarData)
                )
            )
            dao.insert(
                PatternEntity(
                    name = "Glider",
                    data = PatternEntity.convertDataToString(gliderData)
                )
            )
        }.subscribeOn(Schedulers.single())
            .subscribe({}, {})
    }

    private val pulsarData = mutableListOf(
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )

    private val gliderData = mutableListOf(
        mutableListOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )
}