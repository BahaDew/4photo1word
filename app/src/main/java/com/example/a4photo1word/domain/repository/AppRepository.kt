package com.example.a4photo1word.domain.repository

import com.example.a4photo1word.R
import com.example.a4photo1word.data.model.DataQuestion
import com.example.a4photo1word.data.source.MySharedPref


class AppRepository private constructor() {
    private var dataQuestions: ArrayList<DataQuestion> = ArrayList()
    private val levelKey = "LEVEL"
    private val coinKey = "COIN"
    private val isFirstKey = "IS_FIRST"
    private val sharedPref = MySharedPref.getInstance()
    private var level: Int = sharedPref.getInt(levelKey, 0)
    private var coin = sharedPref.getInt(coinKey, 0)
    private val isFirst = sharedPref.getBoolean(isFirstKey, true)
    private val hintBuyCoin = 10
    private val sellCoin = 15


    init {
        if (isFirst) {
            coin = 100
            sharedPref.myPut(coinKey, coin)
            sharedPref.myPut(isFirstKey, false)
        }
    }

    companion object {
        private var instance: AppRepository? = null
        fun getInstance(): AppRepository {
            if (instance == null) {
                instance = AppRepository()
            }
            return instance as AppRepository
        }
    }

    fun getDataQuestion(): DataQuestion {
        return dataQuestions[level]
    }

    fun getHintByCoin(): Boolean {
        if (coin >= hintBuyCoin) {
            coin -= hintBuyCoin
            sharedPref.myPut(coinKey, coin)
            return true
        }
        return false
    }

    fun setCoin(coin: Int) {
        this.coin = coin
        sharedPref.myPut(coinKey, this.coin)
    }

    fun hasCoin(): Boolean {
        return coin >= hintBuyCoin
    }

    fun getCoinCnt(): Int {
        return coin
    }

    fun winUpgradeCoin() {
        coin += sellCoin
        sharedPref.myPut(coinKey, coin)
    }

    fun getLevel(): Int {
        return level
    }

    fun setLevel(level: Int) {
        this.level = level
        saveLevel()
    }

    fun incrementLevel() {
        level++
        saveLevel()
    }

    fun isFull(): Boolean {
        return level >= dataQuestions.size - 1
    }

    init {
        loadData()
    }

    private fun loadData() {
        dataQuestions.add(
            DataQuestion(
                "TABIAT",
                arrayListOf(
                    R.drawable.tabiat1,
                    R.drawable.tabiat2,
                    R.drawable.tabiat3,
                    R.drawable.tabiat4
                )
            )
        )
        dataQuestions.add(
            DataQuestion(
                "BANK",
                arrayListOf(R.drawable.bank1, R.drawable.bank2, R.drawable.bank3, R.drawable.bank4)
            )
        )
        dataQuestions.add(
            DataQuestion(
                "QAXVA",
                arrayListOf(
                    R.drawable.kofee1,
                    R.drawable.kofee2,
                    R.drawable.kofee3,
                    R.drawable.kofee4
                )
            )
        )
        dataQuestions.add(
            DataQuestion(
                "MASHINA",
                arrayListOf(R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4)
            )
        )
        dataQuestions.add(
            DataQuestion(
                "MEVALAR",
                arrayListOf(R.drawable.meva1, R.drawable.meva2, R.drawable.meva3, R.drawable.meva4)
            )
        )
        dataQuestions.add(
            DataQuestion(
                "QISH",
                arrayListOf(
                    R.drawable.winter1,
                    R.drawable.winter2,
                    R.drawable.winter3,
                    R.drawable.winter4
                )
            )
        )
        dataQuestions.add(
            DataQuestion(
                "COMPUTER",
                arrayListOf(R.drawable.comp1, R.drawable.comp2, R.drawable.comp3, R.drawable.comp4)
            )
        )
    }

    private fun saveLevel() {
        sharedPref.myPut(levelKey, level)
    }
}