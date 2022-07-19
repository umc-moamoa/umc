package com.example.umc_hackathon

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

// 설문 작성 페이지의 질문들 목록
class MyRecyclerAdapter2(val questionList: ArrayList<MyQuestion>): RecyclerView.Adapter<MyRecyclerAdapter2.MyViewHolder> (){

    val TAG: String = "<MyRecycleAdapter2>"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.title.setText(questionList.get(position).title)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<EditText>(R.id.et_question)
    }

}