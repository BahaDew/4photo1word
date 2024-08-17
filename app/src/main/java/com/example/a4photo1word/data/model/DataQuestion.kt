package com.example.a4photo1word.data.model

import android.util.Log
import java.util.Collections
import java.util.Random

class DataQuestion(private var answer: String, private var images: List<Int>) {
    private var variant : String
    fun getVariant() : String{
        return variant
    }
    fun getAnswer() : String{
        return answer
    }
    fun getImages() : List<Int>{
        return images
    }
    init {
        val generate: ArrayList<Char> = ArrayList()
        for (c in answer) {
            generate.add(c)
        }
        val random = Random()
        while(generate.size < 12) {
            val c :Char = (random.nextInt(90 - 65 + 1) + 65).toChar()
            if(!generate.contains(c)) {
                generate.add(c)
            }
        }
        generate.shuffle()
        variant = generate.joinToString("")
    }
}