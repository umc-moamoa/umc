package com.example.umc_hackathon.survey

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R

// 설문 조사 문항 확인
class MyAnswerRAdapter(private val myAnswerList: List<MyAnswer>): RecyclerView.Adapter<MyAnswerRAdapter.MyViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_answer_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myAnswerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("MyAnswerRAdapter", "onBindViewHolder() called / position: $position")
        Log.d("haha", itemCount.toString())
        if(itemCount > 0) {
            Log.d("hahaha", myAnswerList[position].result)
            holder.myAnswerTv.text = position.toString() + "번: " + myAnswerList[position].result
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myAnswerTv = itemView.findViewById<TextView>(R.id.my_answer_item_tv)
    }

}