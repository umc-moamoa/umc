package com.umc.seolmunzip.survey

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.umc.seolmunzip.R
import com.umc.seolmunzip.survey.result.ResultAnswer


class AnswerRAdapter(val answerList: List<ResultAnswer>): RecyclerView.Adapter<AnswerRAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.answer_list_item, parent, false)
        Log.d("AnswerViewHolder() / ", "AnswerRAdapter에서 메소드 called")

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.answer.text = answerList[position].result

        Log.d("answer_adapter", " : " +  answerList[position].result)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answer = itemView.findViewById<TextView>(R.id.answer_list_item_tv)
    }

}