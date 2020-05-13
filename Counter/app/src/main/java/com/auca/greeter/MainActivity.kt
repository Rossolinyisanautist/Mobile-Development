package com.auca.greeter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var incrementButton: Button
    lateinit var counterTextView: TextView

    var counter = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incrementButton = findViewById(R.id.incrementButton)
        counterTextView = findViewById(R.id.counterTextView)


        val onClick: (View?) -> Unit = {
            counterTextView.text = getString(R.string.counterTextViewFormat).format(counterTextView.text.toString().toInt() + 1)
        }

        val onClickListener = View.OnClickListener(onClick)

        incrementButton.setOnClickListener(onClickListener)
    }
}
