package com.example.rxjavaretrofit.utils

import android.content.Context
import android.widget.Toast

class Contstants {
    companion object {
        val BASE_URL = "https://api.nomics.com/v1/"
        val API_KEY = "f93053023cfa46fe0174ce04abaeabb1"

        fun showToast(context: Context, position: Int) {
            Toast.makeText(context, "Click Dude..Number: $position", Toast.LENGTH_SHORT).show()
        }
    }
}