package com.auca.final_2

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.auca.final_2.DoodleFragment.OnListFragmentInteractionListener
import com.auca.final_2.dummy.DummyContent.Doodle

import kotlinx.android.synthetic.main.fragment_doodle.view.*


class DoodleRecyclerViewAdapter(
    private val values: List<Doodle>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<DoodleRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Doodle
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_doodle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.nameView.text = item.name

        with(holder.view) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.item_number
        val nameView: TextView = view.content

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }
}
