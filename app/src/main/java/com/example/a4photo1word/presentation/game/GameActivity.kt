package com.example.a4photo1word.presentation.game

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.a4photo1word.R
import com.example.a4photo1word.presentation.dialog.LoseDialog
import com.example.a4photo1word.presentation.dialog.RequestGetHintDialog
import com.example.a4photo1word.presentation.dialog.WinDialog

class GameActivity : AppCompatActivity(), GameContract.View {
    private val answerButtons: ArrayList<TextView> = ArrayList()
    private val variantButtons: ArrayList<TextView> = ArrayList()
    private val images: ArrayList<ImageView> = ArrayList()
    private lateinit var txtLevel: TextView
    private lateinit var txtCoinCnt: TextView
    private lateinit var btnClear: ImageView
    private lateinit var winDialog: WinDialog
    private lateinit var loseDialog: LoseDialog
    private lateinit var presenter: GameContract.Presenter

    @SuppressLint("InternalInsetResource", "DiscouragedApi", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        txtLevel = findViewById(R.id.txt_level)
        btnClear = findViewById(R.id.btn_clear)
        txtCoinCnt = findViewById(R.id.txt_coin_cnt)
        loseDialog = LoseDialog()
        winDialog = WinDialog()
        init()
        presenter = GamePresenter(this)
        val sb = findViewById<View>(R.id.sb)
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            val statusBarHeight = resources.getDimensionPixelSize(resId)
            sb.layoutParams.height = statusBarHeight
        }
    }

    private fun init() {
        initButtons(R.id.answer_btn, answerButtons, 0, this::onClickAnswerButton)
        initButtons(R.id.variants_btn1, variantButtons, 0, this::onClickVariantsButton)
        initButtons(
            R.id.variants_btn2,
            variantButtons,
            variantButtons.size,
            this::onClickVariantsButton
        )
        val img1 = findViewById<ImageView>(R.id.img1)
        img1.setOnClickListener(this::onClickImageQuestion)
        images.add(img1)
        val img2 = findViewById<ImageView>(R.id.img2)
        img2.setOnClickListener(this::onClickImageQuestion)
        images.add(img2)
        val img3 = findViewById<ImageView>(R.id.img3)
        img3.setOnClickListener(this::onClickImageQuestion)
        images.add(img3)
        val img4 = findViewById<ImageView>(R.id.img4)
        img4.setOnClickListener(this::onClickImageQuestion)
        images.add(img4)
        findViewById<ImageView>(R.id.back).setOnClickListener {
            finish()
        }
        btnClear.setOnClickListener {
            answerButtons.forEach {
                onClickAnswerButton(it)
            }
        }
        findViewById<ImageView>(R.id.btn_hint).setOnClickListener {
            presenter.onClickHint()
        }
    }

    private fun initButtons(
        groupId: Int,
        buttons: ArrayList<TextView>,
        index: Int,
        clickButton: View.OnClickListener
    ) {
        val answerButtonGroup: LinearLayout = findViewById(groupId)
        for (i in 0..<answerButtonGroup.childCount) {
            val button: TextView = answerButtonGroup.getChildAt(i) as TextView
            button.setOnClickListener(clickButton)
            button.tag = i + index
            buttons.add(button)
        }
    }

    private fun onClickAnswerButton(v: View) {
        val tag = v.tag as Int
        presenter.onClickAnswerButtons(tag)
    }

    private fun onClickVariantsButton(v: View) {
        val tag = v.tag as Int
        presenter.onClickVariantButtons(tag)
    }

    private fun onClickImageQuestion(v: View) {
        val tag = v.tag as Int
        presenter.onClickImages(tag)
        presenter.onClickBigImage()
    }

    override fun setTextAnswerButton(pos: Int, text: String) {
        answerButtons[pos].text = text
    }

    override fun setTextVariantButton(pos: Int, text: String) {
        variantButtons[pos].text = text
    }

    override fun setVisibleVariantButton(pos: Int, visible: Int) {
        variantButtons[pos].visibility = visible
    }

    override fun setImageQuestion(pos: Int, id: Int) {
        images[pos].setImageResource(id)
        images[pos].tag = id
    }

    override fun setVisibleImage(visible: Int) {
        for (i in 0..<images.size) {
            images[i].visibility = visible
        }
    }

    override fun clearAnswerButtons(pos: Int) {
        for (i in 0..<pos) {
            answerButtons[i].visibility = View.VISIBLE
            answerButtons[i].text = ""
        }
        for (i in pos..<answerButtons.size) {
            answerButtons[i].visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setTextLevel(level: Int) {
        txtLevel.text = (level + 1).toString()
    }

    override fun openWinDialog() {
        if (winDialog.dialog?.isShowing != true) {
            winDialog.show(supportFragmentManager, "win_dialog")
            winDialog.setOnClickTanks {
                presenter.onClickTanks()
                winDialog.dismiss()
            }
        }
    }

    override fun setCoinCnt(coin: Int) {
        txtCoinCnt.text = coin.toString()
    }

    override fun openFinishDialog() {
        val dialog = AlertDialog
            .Builder(this)
            .setCancelable(false)
            .setTitle("Tugadi")
            .setMessage("Tabriklaymiz siz o'yindagi barcha jumboqlarni tugatdingiz. Sizga omad tilaymiz ko'rishguncha xayr !")
            .setPositiveButton("Tugatish") { dialog, _ ->
                dialog.dismiss()
                presenter.onClickFinish()
                finish()
            }
            .create()
        dialog.show()
    }

    override fun openLoseDialog() {
        if (loseDialog.dialog?.isShowing != true)
            loseDialog.show(supportFragmentManager, "lose_dialog")
        loseDialog.setOnClickRetry {
            presenter.onClickRetry()
            loseDialog.dismiss()
        }
    }

    override fun openRequestSellCoinDialog() {
        val requestGetHintDialog = RequestGetHintDialog()
        requestGetHintDialog.setOnClickYes {
            requestGetHintDialog.dismiss()
            presenter.onClickSellCoin()
        }
        requestGetHintDialog.show(supportFragmentManager, "RequestGetHintDialog")
    }

    override fun openNotCoinDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Xatolik")
        builder.setMessage("Sizda yetarlicha tanga yo'q !")
        builder.setPositiveButton("Tushunarli") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create()
        val dialog = builder.create()
        dialog.show()
    }

    override fun setGoneBigImage() {

    }

    override fun setImageBigImage(id: Int) {}
}