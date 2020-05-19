package com.auca.final_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auca.final_2.dummy.DummyContent.Doodle

class DoodleActivity : AppCompatActivity() {

    private lateinit var doodleView: DoodleView
    private lateinit var doodle: Doodle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        doodle = intent.getSerializableExtra("doodle") as Doodle
        doodleView = findViewById(R.id.doodleView)

        doodleView.leadDoodle(doodle.path)
    }

    override fun onDestroy() {
        super.onDestroy()
        doodleView.saveDoodle(doodle.path)
    }
}