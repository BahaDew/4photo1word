package com.example.a4photo1word.presentation.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.a4photo1word.R
import kotlin.math.log

class ExitDialog : DialogFragment() {
    private var onClickYes: (() -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_exit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.btn_yes).setOnClickListener {
            onClickYes?.invoke()
            Log.d("DIALOG", "onViewCreated: $onClickYes")
        }
        view.findViewById<View>(R.id.btn_no).setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
    }

    fun setOnClickYes(onClickYes: (() -> Unit)) {
        this.onClickYes = onClickYes
    }
}