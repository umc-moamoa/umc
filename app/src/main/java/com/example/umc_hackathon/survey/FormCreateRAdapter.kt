package com.example.umc_hackathon.survey

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R

// 설문 작성 페이지의 질문들 목록
class FormCreateRAdapter(val questionList: ArrayList<MyQuestion>): RecyclerView.Adapter<FormCreateRAdapter.MyViewHolder> (){

    val TAG: String = "<MyRecycleAdapter2>"

    fun addItem(item: MyQuestion) {
        questionList.add(item)
        notifyDataSetChanged()

    }

    fun removeItem(position: Int) {
        questionList.removeAt(position)
        notifyDataSetChanged()
    }

    fun modifyItem(position: Int, item: MyQuestion) {
        questionList.set(position, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_question_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.title.setText(questionList.get(position).title)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.question_item_et)
    }

}