package com.example.umc_hackathon.post

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R


class MyPointRAdapter(val pointList: List<PointHistoryRecent>): RecyclerView.Adapter<MyPointRAdapter.MyViewHolder>() {

    val TAG: String = "<MyPointRAdapter>"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_point_item, parent, false)
        Log.d("MyPointViewHolder() / ", "MyPointRAdapter에서 메소드 called")

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pointList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.addAmount.text = pointList[position].addAmount.toString()
        holder.subAmount.text = pointList[position].subAmount.toString()
        holder.date.text = pointList[position].created.toString()
        Log.d("date", " : " +  pointList[position].created)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val addAmount = itemView.findViewById<TextView>(R.id.board_point_item_point_tv)
        val subAmount = itemView.findViewById<TextView>(R.id.board_point_item_state_tv)
        val date = itemView.findViewById<TextView>(R.id.board_point_item_date_tv)
    }

}