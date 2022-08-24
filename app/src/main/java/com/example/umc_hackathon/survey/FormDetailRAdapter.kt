package com.example.umc_hackathon.survey

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R

// 설문 조사 문항 확인
class FormDetailRAdapter(val questionList: List<FormDetail>): RecyclerView.Adapter<FormDetailRAdapter.MyViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_question_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("FormDetailRAdapter", "onBindViewHolder() called / position: $position")
        holder.title.text = (position + 1).toString() + "번. " + questionList[position].question

        if(questionList[position].items.size >= 2) {
            var result: String = ""
            for(i in 0 until questionList[position].items.size) {
                result += "${i + 1}번 " + questionList[position].items[i] + "\n"
            }
            holder.option.text = result
        } else {
            holder.option.text = ""
            holder.option.hint = "답변을 입력하세요"
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.question_item_et)
        val option = itemView.findViewById<TextView>(R.id.question_item_option_tv)
    }
}