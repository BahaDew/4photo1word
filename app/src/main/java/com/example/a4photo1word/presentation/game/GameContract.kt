package com.example.a4photo1word.presentation.game

import com.example.a4photo1word.data.model.DataQuestion

interface GameContract {
    interface Model {
        fun getQuestion() : DataQuestion
        fun setLevel(level : Int)
        fun incrementLevel()
        fun getLevel() : Int
        fun getCoinCnt() : Int
        fun isFull() : Boolean
        fun hasCoin() : Boolean
        fun winUpgradeCoin()
        fun getHintByCoin()
        fun setCoin(coin: Int)
    }
    interface View {
        fun setTextAnswerButton(pos : Int, text : String)
        fun setTextVariantButton(pos : Int, text : String)
        fun setVisibleVariantButton(pos : Int, visible : Int)
        fun setImageQuestion(pos : Int, id : Int)
        fun setVisibleImage(visible : Int)
        fun clearAnswerButtons(pos : Int)
        fun setGoneBigImage()
        fun setImageBigImage(id : Int)
        fun setTextLevel(level : Int)
        fun openWinDialog()
        fun setCoinCnt(coin : Int)
        fun openFinishDialog()
        fun openLoseDialog()
        fun openRequestSellCoinDialog()
        fun openNotCoinDialog()
    }
    interface Presenter {
        fun onClickAnswerButtons(pos : Int)
        fun onClickVariantButtons(pos : Int)
        fun onClickImages(id : Int)
        fun onClickBigImage()
        fun onClickTanks()
        fun onClickHint()
        fun onClickSellCoin()
        fun onClickRetry()
        fun onClickFinish()
    }
}