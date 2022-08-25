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
class FormDetailRAdapter(val questionList: List<FormDetail>): RecyclerView.Adapter<FormDetailRAdapter.MyViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_question_input_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("FormDetailRAdapter", "onBindViewHolder() called / position: $position")
        holder.title.text = (position + 1).toString() + "번. " + questionList[position].question

        // 질문 유형에 따라 다르게 보이기
        if(questionList[position].format == 1) {
            for(i in 0 until questionList[position].items.size) {
                Log.d("items", questionList[position].items[i])

                val radioButton: RadioButton = RadioButton(holder.itemView.context)
                radioButton.text = questionList[position].items[i]
                holder.optionRg.addView(radioButton)
            }

            holder.optionRg.visibility = View.VISIBLE
            holder.optionCb.visibility = View.GONE
            holder.optionShort.visibility = View.GONE
            holder.optionLong.visibility = View.GONE
        } else if(questionList[position].format == 2) {
            for(i in 0 until questionList[position].items.size) {
                Log.d("items", questionList[position].items[i])

                val checkBox: CheckBox = CheckBox(holder.itemView.context)
                checkBox.text = questionList[position].items[i]
                holder.optionCb.addView(checkBox)
            }

            holder.optionRg.visibility = View.GONE
            holder.optionCb.visibility = View.VISIBLE
            holder.optionShort.visibility = View.GONE
            holder.optionLong.visibility = View.GONE
        } else if (questionList[position].format == 3) {
            holder.optionRg.visibility = View.GONE
            holder.optionCb.visibility = View.GONE
            holder.optionShort.visibility = View.VISIBLE
            holder.optionLong.visibility = View.GONE
        } else if (questionList[position].format == 4) {
            holder.optionRg.visibility = View.GONE
            holder.optionCb.visibility = View.GONE
            holder.optionShort.visibility = View.GONE
            holder.optionLong.visibility = View.VISIBLE
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.question_input_item_tv)
        val optionRg = itemView.findViewById<RadioGroup>(R.id.question_input_item_rg)
        val optionCb = itemView.findViewById<LinearLayout>(R.id.question_input_checkbox_ll)
        val optionShort = itemView.findViewById<EditText>(R.id.question_input_short_answer_et)
        val optionLong = itemView.findViewById<EditText>(R.id.question_input_long_answer_et)
    }

}