package com.auca.final_2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.auca.final_2.DoodleRecyclerViewAdapter
import com.auca.final_2.R
import com.auca.final_2.dummy.DummyContent
import com.auca.final_2.dummy.DummyContent.Doodle

class DoodleFragment : Fragment() {
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Doodle?)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DoodleFragment()
    }

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doodle_list, container, false) as RecyclerView
        if(view is RecyclerView) {
            DummyContent.generateSampleDoodles(context!! )
            view.adapter = DoodleRecyclerViewAdapter(DummyContent.ITEMS, listener)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}