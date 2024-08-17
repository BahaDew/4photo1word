package com.example.a4photo1word.presentation.game

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.example.a4photo1word.data.model.DataQuestion

class GamePresenter(private var view: GameContract.View) : GameContract.Presenter {

    private var model: GameContract.Model = GameModel()
    private var question: DataQuestion = model.getQuestion()
    private lateinit var variants: String
    private lateinit var answers: ArrayList<Int?>

    init {
        nextQuestion()
        view.setCoinCnt(model.getCoinCnt())
    }

    override fun onClickAnswerButtons(pos: Int) {
        if (pos >= answers.size || pos < 0) return
        val index: Int? = answers[pos]
        if (index != null) {
            answers[pos] = null
            view.setVisibleVariantButton(index, View.VISIBLE)
            view.setTextAnswerButton(pos, "")
        }
    }

    override fun onClickVariantButtons(pos: Int) {
        var index: Int = answers.indexOf(null)
        if (index != -1) {
            view.setTextAnswerButton(index, variants[pos].toString())
            view.setVisibleVariantButton(pos, View.INVISIBLE)
            answers[index] = pos
        }
        index = answers.indexOf(null)
        if (index == -1) {
            if (isWin()) {
                if (!model.isFull()) {
                    model.incrementLevel()
                    Handler(Looper.getMainLooper()).postDelayed({
                        view.openWinDialog()
                        model.winUpgradeCoin()
                        view.setCoinCnt(model.getCoinCnt())
                    }, 300)
                } else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        view.openFinishDialog()
                        model.setLevel(0)
                        model.setCoin(0)
                    }, 300)
                }
            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    view.openLoseDialog()
                }, 300)
            }
        }
    }

    override fun onClickImages(id: Int) {
        view.setImageBigImage(id)
        view.setVisibleImage(View.INVISIBLE)
    }

    override fun onClickBigImage() {
        view.setVisibleImage(View.VISIBLE)
        view.setGoneBigImage()
    }

    override fun onClickTanks() {
        nextQuestion()
    }

    override fun onClickHint() {
        if (model.hasCoin())
            view.openRequestSellCoinDialog()
        else {
            view.openNotCoinDialog()
        }
    }

    override fun onClickSellCoin() {
        model.getHintByCoin()
        view.setCoinCnt(model.getCoinCnt())
        for (i in answers.indices) {
            Log.d("BBB", "onClickSellCoin: i = $i -> andswer[i] -> ${answers[i]}")
            Log.d("BBB", "onClickSellCoin: variant[answer[i]] -> ${variants[answers[i] ?: 0]}")
            if (answers[i] == null) {
                val temp = question.getAnswer()[i]
                for (j in variants.indices) {
                    if (variants[j] == temp && answers.find { it == j } == null) {
                        onClickVariantButtons(j)
                        break
                    }
                }
                break
            } else if (variants[answers[i]!!] != question.getAnswer()[i]) {
                val temp = question.getAnswer()[i]
                for (j in variants.indices) {
                    Log.d(
                        "BBB",
                        "else if -> j => $j variants[j] -> ${variants[j]} find ${answers.find { it == j }}}"
                    )
                    if (variants[j] == temp && answers.find { it == j } == null) {
                        onClickAnswerButtons(i)
                        onClickVariantButtons(j)
                        break
                    }
                }
                break
            }
        }
    }

    override fun onClickRetry() {
        nextQuestion()
    }

    override fun onClickFinish() {
        model.setCoin(0)
        model.setLevel(0)
    }

    private fun nextQuestion() {
        question = model.getQuestion()
        val answer: String = question.getAnswer()
        view.clearAnswerButtons(answer.length)
        answers = ArrayList()
        for (i in 1..answer.length) {
            answers.add(null)
        }
        variants = question.getVariant()
        for (i in variants.indices) {
            view.setVisibleVariantButton(i, View.VISIBLE)
            view.setTextVariantButton(i, variants[i].toString())
        }
        val images: List<Int> = question.getImages()
        for (i in images.indices) {
            view.setImageQuestion(i, images[i])
        }
        view.setTextLevel(model.getLevel())
    }

    private fun isWin(): Boolean {
        var resul = String()
        for (i in answers) {
            if (i != null)
                resul += variants[i].toString()
        }
        return question.getAnswer() == resul
    }
}