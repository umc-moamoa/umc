package com.example.umc_hackathon

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class MyPointRAdapter(val pointList: ArrayList<MyPoint>): RecyclerView.Adapter<MyPointRAdapter.MyViewHolder>() {

    val TAG: String = "<MyPointRAdapter>"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_point_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pointList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.state.text = pointList.get(position).state
        holder.date.text = pointList.get(position).date
        holder.point.text = pointList.get(position).point.toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val state = itemView.findViewById<TextView>(R.id.board_point_item_state_tv)
        val date = itemView.findViewById<TextView>(R.id.board_point_item_date_tv)
        val point = itemView.findViewById<TextView>(R.id.board_point_item_point_tv)
    }

}