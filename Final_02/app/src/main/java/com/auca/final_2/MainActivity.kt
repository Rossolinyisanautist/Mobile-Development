package com.auca.final_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auca.final_2.dummy.DummyContent.Doodle

class MainActivity : AppCompatActivity(), DoodleFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.placeholder, DoodleFragment.newInstance())
            .commit()
    }

    override fun onListFragmentInteraction(item: Doodle?) {
        val intent = Intent(this, DoodleActivity::class.java)
            .apply {
                putExtra("doodle", item)
            }
        startActivity(intent)
    }

}