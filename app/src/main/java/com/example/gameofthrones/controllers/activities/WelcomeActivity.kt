package com.example.gameofthrones.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.gameofthrones.R
import com.example.gameofthrones.models.QuotesApi

class WelcomeActivity : AppCompatActivity() {

    private var quote= ""

    private var quoteText: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteText = findViewById(R.id.quotetextView) as TextView
        getQuotes()
    }
    
    fun getQuotes() {

        QuotesApi.apply {
            requestQuotes(
                {
                    it?.apply {
                        quoteText!!.setText(it.quote)
                        Log.d("Llego", it.quote)
                    }
                },
                {
                    Log.d("Error", "Ocurrio un error")
                }
            )
        }

    }
}
