package com.example.a4photo1word.presentation.game

import com.example.a4photo1word.data.model.DataQuestion
import com.example.a4photo1word.domain.repository.AppRepository

class GameModel : GameContract.Model {
    private val repository : AppRepository = AppRepository.getInstance()
    override fun getQuestion() : DataQuestion{
        return repository.getDataQuestion()
    }

    override fun setLevel(level: Int) {
        repository.setLevel(level)
    }

    override fun incrementLevel() {
        repository.incrementLevel()
    }

    override fun getLevel(): Int {
        return repository.getLevel()
    }

    override fun getCoinCnt(): Int {
        return repository.getCoinCnt()
    }

    override fun isFull(): Boolean {
        return repository.isFull()
    }

    override fun hasCoin(): Boolean {
        return repository.hasCoin()
    }

    override fun winUpgradeCoin() {
        repository.winUpgradeCoin()
    }

    override fun getHintByCoin() {
        repository.getHintByCoin()
    }

    override fun setCoin(coin: Int) {
        repository.setCoin(coin)
    }
}