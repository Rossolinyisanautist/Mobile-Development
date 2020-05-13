package com.auca.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    companion object {
        val DIE_1 = "Die1"
        val DIE_2 = "Die2"
    }
    val images = intArrayOf(
        R.drawable.ic_dice_1,
        R.drawable.ic_dice_2,
        R.drawable.ic_dice_3,
        R.drawable.ic_dice_4,
        R.drawable.ic_dice_5,
        R.drawable.ic_dice_6
    )

    lateinit var dice1Img : ImageView
    lateinit var dice2Img : ImageView
    lateinit var rollButton : Button

    lateinit var dice : Dice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice = Dice()

        dice1Img = findViewById(R.id.imageView)
        dice2Img = findViewById(R.id.imageView2)
        rollButton = findViewById(R.id.button6)

        if(savedInstanceState != null) {
            dice.first = savedInstanceState.getInt(DIE_1)
            dice.second = savedInstanceState.getInt(DIE_2)

            updateImages()
        }

        rollButton.setOnClickListener{
            dice.roll()

            updateImages()
        }
    }

    private fun updateImages() {
        dice1Img.setImageResource(images[dice.first - 1])
        dice2Img.setImageResource(images[dice.second - 1])
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(DIE_1, dice.first)
        outState.putInt(DIE_2 , dice.second)
    }
}
