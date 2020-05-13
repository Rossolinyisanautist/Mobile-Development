package com.auca.dice

import kotlin.random.Random

class Dice {

    var first = Random.nextInt(1, 7)
    var second = Random.nextInt(1, 7)

    fun roll() {
        first = Random.nextInt(1, 7)
        second = Random.nextInt(1, 7)
    }
}