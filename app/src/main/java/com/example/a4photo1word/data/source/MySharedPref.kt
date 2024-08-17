package com.example.a4photo1word.data.source

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class MySharedPref private constructor(private var context : Context){

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var instance : MySharedPref
        fun getInstance() : MySharedPref {
            return instance
        }
        fun init(context: Context) {
            if(!(::instance.isInitialized)) {
                instance = MySharedPref(context)
            }
        }
    }

    private val sharedPr = context.getSharedPreferences("4pic1word", Context.MODE_PRIVATE)

    fun myPut(key : String, value : Int) {
        sharedPr.edit().putInt(key, value).apply()
    }
    fun myPut(key : String, value : String) {
        sharedPr.edit().putString(key, value).apply()
    }
    fun myPut(key : String, value : Boolean) {
        sharedPr.edit().putBoolean(key, value).apply()
    }
    fun getInt(key : String, defaultValue: Int) : Int {
        return sharedPr.getInt(key, defaultValue)
    }
    fun getString(key : String, defaultValue: String) : String? {
        return sharedPr.getString(key, defaultValue)
    }
    fun getBoolean(key : String, defaultValue: Boolean) : Boolean {
        return sharedPr.getBoolean(key, defaultValue)
    }
}