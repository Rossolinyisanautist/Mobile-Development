package com.auca.doodlerisserialmurderer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auca.doodlerisserialmurderer.dummy.DummyContent  .Doodle

class MainActivity : AppCompatActivity(), DoodleFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.placeHolder, DoodleFragment. newInstance())
            .commit()

    }

    override fun onListFragmentInteraction(item: Doodle?) {
        val intent = Intent(this, DoodleActivity::class.java)

        startActivity(intent)
    }

}
